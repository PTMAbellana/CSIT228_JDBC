package com.example.csit228_f1_v2;

import java.sql.*;

public class CreateTable {
    public static void main(String[] args) {
        Connection c = MySQLConnection.getConnection();
        try {
            createUsersTable(c);
            createProductsTable(c);
            System.out.println("Tables have been created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createUsersTable(Connection c) throws SQLException {
        String usersQuery = "CREATE TABLE users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "email VARCHAR(50) NOT NULL," +
                "username VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL)";
        try (Statement statement = c.createStatement()) {
            statement.execute(usersQuery);
            System.out.println("Users table has been created successfully.");
        }
    }

    private static void createProductsTable(Connection c) throws SQLException {
        String productsQuery = "CREATE TABLE products (" +
                "prodid INT PRIMARY KEY AUTO_INCREMENT," +
                "userid INT," +
                "prodname VARCHAR(255),"+
                "price DECIMAL(10,2)," +
                "description VARCHAR(255)," +
                "FOREIGN KEY (userid) REFERENCES users(id))";
        try (Statement statement = c.createStatement()) {
            statement.execute(productsQuery);
            System.out.println("Products table has been created successfully.");
        }
    }

    public static boolean productListTable(){
        try (Connection c = MySQLConnection.getConnection()) {
            DatabaseMetaData metaData = c.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "products", null);
            return resultSet.next(); // If next() returns true, table exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }//sakto
}

