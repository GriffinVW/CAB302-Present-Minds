package com.example.newplan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button settings;
    @FXML
    private Button home;
    @FXML
    private Button reminders;
    @FXML
    private Button restrictions;
    @FXML
    private Button information;
    @FXML
    private Button report;


//    Make Conterller interface

    @FXML
    private void initialize() {
        System.out.println("Test");
        settings.setOnAction(event -> handleButtonClick());
        home.setOnAction(event -> handleButtonClick());
        reminders.setOnAction(event -> handleButtonClick());
        restrictions.setOnAction(event -> handleButtonClick());
        information.setOnAction(event -> handleButtonClick());
        report.setOnAction(event -> handleButtonClick());
    }

    private void handleButtonClick() {
        System.out.println("Button clicked!");
        // Add your functionality here
    }

}