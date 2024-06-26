package com.example.csit228_f1_v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/abellana_jdbc";
    public static final String USERNAME = "paul";
    public static final String PASSWORD = "abellana";
    static Connection getConnection(){
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USERNAME,PASSWORD);
            System.out.println("DB Connection Success!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
