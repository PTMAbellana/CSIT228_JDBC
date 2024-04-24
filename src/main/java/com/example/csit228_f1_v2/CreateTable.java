package com.example.csit228_f1_v2;

import java.sql.*;

public class CreateTable {
    public static void createTables() {
        Connection c = MySQLConnection.getConnection();
        String usersQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "email VARCHAR(50) NOT NULL," +
                "username VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL)";

        String productsQuery = "CREATE TABLE IF NOT EXISTS products (" +
                "prodid INT PRIMARY KEY AUTO_INCREMENT," +
                "userid INT," +
                "prodname VARCHAR(255)," +
                "price DECIMAL(10,2)," +
                "description VARCHAR(255)," +
                "FOREIGN KEY (userid) REFERENCES users(id))";
        try {
            Statement statement = c.createStatement();
            statement.execute(usersQuery);
            statement.execute(productsQuery);

            System.out.println("Tables created successfully!");
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

    public static boolean productListTable(){
        try (Connection c = MySQLConnection.getConnection()) {
            DatabaseMetaData metaData = c.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "products", null);
            return resultSet.next(); // If next() returns true, table exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

