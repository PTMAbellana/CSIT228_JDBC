package com.example.csit228_f1_v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        CreateTable.createTables();
        HelloApplication.primaryStage = primaryStage; //Assigning primary stage to the static variable
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("JDBC Activity!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
    public static void setHelloView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene); // Using the static primaryStage variable
    }

    public static void setLoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene); // Using the static primaryStage variable
    }

    public static void setRegisterScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene); // Using the static primaryStage variable
    }

    public static void setUpdateScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("update-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene); // Using the static primaryStage variable
    }
    public static void setStartingScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene); // Using the static primaryStage variable
    }

    public static void setHomepageScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homepage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene); // Using the static primaryStage variable
    }


}
