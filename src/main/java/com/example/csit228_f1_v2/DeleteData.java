package com.example.csit228_f1_v2;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void deleteData(Integer id) {
        try (Connection c = MySQLConnection.getConnection()) {
            // Begin the transaction
            c.setAutoCommit(false);
            deleteAllProducts(id);

            try {
                PreparedStatement statement = c.prepareStatement(
                        "DELETE FROM users WHERE id = ?"
                );
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();

                // Commit the transaction
                c.commit();

                // Show alert
                showAlert("User Data Deleted", "User data has been deleted successfully.");
                Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
                HelloApplication.setHelloView();
            } catch (SQLException e) {
                // Rollback the transaction if an exception occurs
                c.rollback();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProduct(int id) {
        try (Connection c = MySQLConnection.getConnection()) {
            // Begin the transaction
            c.setAutoCommit(false);

            try {
                PreparedStatement statement = c.prepareStatement(
                        "DELETE FROM products WHERE prodid=?"
                );
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();

                // Commit the transaction
                c.commit();

                // Show alert
                showAlert("Product Deleted", "Product has been deleted successfully.");
            } catch (SQLException e) {
                // Rollback the transaction if an exception occurs
                c.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAllProducts(int id) {
        try (Connection c = MySQLConnection.getConnection()) {
            // Begin the transaction
            c.setAutoCommit(false);

            try {
                PreparedStatement statement = c.prepareStatement(
                        "DELETE FROM products WHERE userid=?"
                );
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();

                // Commit the transaction
                c.commit();

                // Show alert
                showAlert("All Products Deleted", "All products have been deleted successfully.");
            } catch (SQLException e) {
                // Rollback the transaction if an exception occurs
                c.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
