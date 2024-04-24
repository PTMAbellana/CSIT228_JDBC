package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateView {
    @FXML
    Button btnChangePassword;
    @FXML
    TextField tfUsername;
    @FXML
    PasswordField pfOldPassword;
    @FXML
    PasswordField pfNewPassword;
    @FXML
    Button btnBackToHomePage;
    public void changePassword() throws IOException {
        String username = tfUsername.getText();
        String oldPassword = pfOldPassword.getText();
        String newPassword = pfNewPassword.getText();
        UpdateData.updateData(username,oldPassword, newPassword);
    }

    public void BackToHomePage() throws IOException {
        Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
        HelloApplication.setHomepageScene();
    }

}