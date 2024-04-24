package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void updateData(String username, String oldPassword, String newPassword) {
        try (Connection c = MySQLConnection.getConnection()) {
            c.setAutoCommit(false); // Begin the transaction
            try {
                if (ReadData.readData(username, oldPassword)) {
                    PreparedStatement statement = c.prepareStatement(
                            "UPDATE users SET password = ? WHERE username = ?"
                    );
                    statement.setString(1, newPassword);
                    statement.setString(2, username);

                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        showAlert("Password updated successfully.");
                    } else {
                        showAlert("Failed to update password.");
                    }
                } else {
                    showAlert("Invalid username/password.");
                }

                c.commit(); // Commit the transaction
            } catch (SQLException e) {
                c.rollback(); // Rollback the transaction if an exception occurs
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProduct(int product_id, String new_name, Double new_price, String new_description) {
        try (Connection c = MySQLConnection.getConnection()) {
            c.setAutoCommit(false); // Begin the transaction

            try {
                PreparedStatement statement = c.prepareStatement(
                        "UPDATE products SET prodname=?, price=?, description=? WHERE prodid=?"
                );
                statement.setString(1, new_name);
                statement.setDouble(2, new_price);
                statement.setString(3, new_description);
                statement.setInt(4, product_id);

                int updates = statement.executeUpdate();
                if (updates > 0) {
                    showAlert("Product updated successfully.");
                } else {
                    showAlert("Failed to update product.");
                }

                c.commit(); // Commit the transaction
            } catch (SQLException e) {
                c.rollback(); // Rollback the transaction if an exception occurs
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
