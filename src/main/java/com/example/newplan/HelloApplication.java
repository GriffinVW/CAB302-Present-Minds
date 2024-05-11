package com.example.newplan;

import com.example.newplan.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SessionManager sessionManager = SessionManager.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Present-Minds!");
        stage.setScene(scene);
        stage.show();
    }
    // Toms comment
    // Ethan did not commit here
    // Griffins second commit for webhook testing

    public static void main(String[] args) {
        // added this line quickly to see where the .db file was stored
        System.out.println("Working directory = " + System.getProperty("user.dir"));
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();

        launch();
        userDAO.close();
    }
}