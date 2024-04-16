package com.example.csit228_f1_v2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?,?)"
             )){
            String name = "Vince";
            String password = "serato";

            statement.setString(1, name); //index starts at 1 for sql
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate(); //CREATE, UPDATE, DELETE
            System.out.println("Rows Inserted: " + rowsInserted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
