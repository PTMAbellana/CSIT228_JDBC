package com.example.csit228_f1_v2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    @FXML
    Button btnSignIn;
    @FXML
    TextField tfUsername;
    @FXML
    PasswordField pfPassword;
    @FXML
    Label lblcheck;
    public void login (){
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        int id = ReadData.readData(username);
        if(ReadData.readData(username, password)){
            try {
                HomeController hc = new HomeController();
                hc.setLoggedInID(id);
                Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
                HelloApplication.setHomepageScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
                lblcheck.setText("Invalid username/password");
//                    actionTarget.setOpacity(1);
        }
    }
    public void backToHelloView() throws IOException {
        Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
        HelloApplication.setHelloView();
    }

}
