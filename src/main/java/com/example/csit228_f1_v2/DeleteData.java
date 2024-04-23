package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    @FXML
    Button btnSignIn;
    @FXML
    TextField tfUsername;
    @FXML
    PasswordField pfPassword;

    public static void deleteData (Integer id){
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM users WHERE id = ?"
            );
        ) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("User Data Deleted: " + rowsDeleted);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

