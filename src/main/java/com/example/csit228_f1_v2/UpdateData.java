package com.example.csit228_f1_v2;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void updateData(String username, String oldPassword, String newPassword) {
        try (Connection c = MySQLConnection.getConnection()) {
            if (ReadData.readData(username, oldPassword)) {
                // Prepare the update statement
                PreparedStatement statement = c.prepareStatement(
                        "UPDATE users SET password = ? WHERE username = ?"
                );

                // Set the new password and username as parameters
                statement.setString(1, newPassword);
                statement.setString(2, username);

                // Execute the update statement
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Password updated successfully.");
                } else {
                    System.out.println("Failed to update password.");
                }
            } else {
                System.out.println("Invalid username/password.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProduct(int product_id, String new_name, String new_description){
        try (
                Connection c = MySQLConnection.getConnection();
                PreparedStatement statement = c.prepareStatement(
                        "UPDATE products SET prodname=?, description=? WHERE prodid=?"
                );
        ){
            statement.setString(1,new_name);
            statement.setString(2,new_description);
            statement.setInt(3,product_id);

            int updates = statement.executeUpdate();
            System.out.println("Rows updated (Product): " + updates);
        } catch (SQLException  e){
            e.printStackTrace();
        }
    }
}
