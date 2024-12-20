package com.example.health;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        if (Patient.right) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advice-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Health Tracker");
            stage.setScene(scene);
            stage.show();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Registration");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}