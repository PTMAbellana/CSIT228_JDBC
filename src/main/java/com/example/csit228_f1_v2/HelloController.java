package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class HelloController {
    public GridPane pnLogin;
    public AnchorPane pnMain;
    public VBox pnHome;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onSigninClick() throws IOException {
        Parent homeview = FXMLLoader.load(HelloApplication.class
                .getResource("home-view.fxml"));
        AnchorPane p = (AnchorPane) pnLogin.getParent();
        p.getChildren().remove(pnLogin);
        p.getChildren().add(homeview);
    }

    public void insertData(String username, String password) {
            try (Connection c = MySQLConnection.getConnection();
                 PreparedStatement statement = c.prepareStatement(
                         "INSERT INTO users (username, password) VALUES (?,?)"
                 )){
                statement.setString(1, username); //index starts at 1 for sql
                statement.setString(2, password);
                int rowsInserted = statement.executeUpdate(); //CREATE, UPDATE, DELETE
                System.out.println("Rows Inserted: " + rowsInserted);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
    public static Boolean readData(String username, String password) {
        try (Connection c = MySQLConnection.getConnection();){
            Statement statement = c.createStatement();
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query); //FOR READING
            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("username");
                String pass = res.getString("password");
                if(name.equals(username) && pass.equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}