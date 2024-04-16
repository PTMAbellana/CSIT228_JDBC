package com.example.csit228_f1_v2;
import java.sql.*;

public class ReadData {
    public static void readData() {
        try (Connection c = MySQLConnection.getConnection();){
            Statement statement = c.createStatement();
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query); //FOR READING
            while(res.next()){
                int id = res.getInt("id");
                String username = res.getString("username");
                String password = res.getString(3);
                System.out.println("ID: " + id + "\nUsername: " + username + "\nPassword: " + password + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}