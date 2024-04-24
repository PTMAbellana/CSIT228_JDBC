package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void register() throws IOException {
        Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
        HelloApplication.setRegisterScene();
    }

    @FXML
    public void login() throws IOException {
        Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
        HelloApplication.setLoginScene();
    }

}
