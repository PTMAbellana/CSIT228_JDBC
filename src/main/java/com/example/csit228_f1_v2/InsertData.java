package com.example.csit228_f1_v2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void insertData(String name, String email, String username, String password) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (name, email, username, password) VALUES (?,?,?,?)"
             )){
            statement.setString(1, name); //index starts at 1 for sql
            statement.setString(2, email);
            statement.setString(3, username);
            statement.setString(4, password);
            int rowsInserted = statement.executeUpdate(); //CREATE, UPDATE, DELETE
            System.out.println("Rows Inserted: " + rowsInserted);
            try {
                Parent p = FXMLLoader.load(InsertData.class.getResource("homepage.fxml"));
                Scene s = new Scene(p);
                Stage stage = new Stage();
                stage.setScene(s);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
