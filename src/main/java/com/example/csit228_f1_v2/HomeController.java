package com.example.csit228_f1_v2;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.csit228_f1_v2.DeleteData.deleteData;

public class HomeController {

    public ToggleButton tbNight;
    public ProgressIndicator piProgress;
    public Slider slSlider;
    public ProgressBar pbProgress;
    private static Integer loggedInID;
//    @FXML
//    private Label welcomeLabel;
//    @FXML
//    private AnchorPane rootPane;

    // Method to set the logged-in username
    public void setLoggedInID(Integer id) {
        this.loggedInID = id;
//        welcomeLabel.setText("Welcome, " + username + "!");
    }
    public void onSliderChange() {
        double val = slSlider.getValue();
        System.out.println(val);
        piProgress.setProgress(val/100);
        pbProgress.setProgress(val/100);
        if (val == 100) {
            System.exit(0);
        }
    }

    public void onNightModeClick() {
        if (tbNight.isSelected()) {
            tbNight.getParent().setStyle("-fx-background-color: BLACK");
            tbNight.setText("DAY");
        } else {
            tbNight.getParent().setStyle("-fx-background-color: WHITE");
            tbNight.setText("NIGHT");
        }
    }
    public void signout(){
        try {
            Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
            HelloApplication.setStartingScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteAccount(){
        deleteData(loggedInID);
    }

    public void updateAccount(){
        try {
            Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
            HelloApplication.setUpdateScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
