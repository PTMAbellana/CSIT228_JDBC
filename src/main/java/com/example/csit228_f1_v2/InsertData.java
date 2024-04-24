package com.example.csit228_f1_v2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InsertData {
    public static void insertData(String name, String email, String username, String password) {
        try (Connection c = MySQLConnection.getConnection()) {
            // Begin transaction
            c.setAutoCommit(false);

            try {
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO users (name, email, username, password) VALUES (?,?,?,?)"
                );
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, username);
                statement.setString(4, password);
                int rowsInserted = statement.executeUpdate();
                System.out.println("Rows Inserted: " + rowsInserted);

                // Commit transaction
                c.commit();

                // Show alert for successful insertion
                showAlert(AlertType.INFORMATION, "Success", "User inserted successfully!");
            } catch (SQLException e) {
                // Rollback transaction if an exception occurs
                c.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertProductData(String productName, double productPrice, String productDescription) {
        try (Connection c = MySQLConnection.getConnection()) {
            // Begin transaction
            c.setAutoCommit(false);

            try {
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO products (userid, prodname, price, description) VALUES (?,?,?,?)"
                );
                statement.setInt(1, HomeController.loggedInID);
                statement.setString(2, productName);
                statement.setDouble(3, productPrice);
                statement.setString(4, productDescription);
                int rowsInserted = statement.executeUpdate();
                System.out.println("Rows Inserted: " + rowsInserted);

                // Commit transaction
                c.commit();

                // Show alert for successful insertion
                showAlert(AlertType.INFORMATION, "Success", "Product inserted successfully!");
            } catch (SQLException e) {
                // Rollback transaction if an exception occurs
                c.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to display an alert
    private static void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
