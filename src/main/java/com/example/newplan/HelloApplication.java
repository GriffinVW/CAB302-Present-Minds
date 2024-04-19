package com.example.newplan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Present-Minds!");
        stage.setScene(scene);
        stage.show();
    }
    // Toms comment
    // Ethan did not commit here

    public static void main(String[] args) {
        launch();
    }
}