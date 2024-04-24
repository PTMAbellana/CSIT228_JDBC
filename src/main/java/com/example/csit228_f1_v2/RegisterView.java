package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterView {
    @FXML
    Button btnSignUp;
    @FXML
    TextField tfName;
    @FXML
    TextField tfEmail;
    @FXML
    TextField tfUsername;
    @FXML
    PasswordField pfPassword;
    public void signUp() throws IOException {
        String name = tfName.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        InsertData.insertData(name,email,username,password);
        Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
        HelloApplication.setRegisterScene();
    }
    public void backToHelloView() throws IOException {
        Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
        HelloApplication.setHelloView();
    }
}
