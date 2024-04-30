package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// This is an example controller,
public class exampleContoller implements Controller {

    // Label the buttons you need, below are the buttons for the nav, They shouldn't be included in the login or signup pages

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
    @FXML
    private Button example;

    // Label the text fields and checkboxes : JUST ADD AN fx:id TO THE INPUT/CHECK BOX TO DO THIS
    @FXML
    private TextField first_name;


    // JUST REMEMBER FOR EVERY TEXT FILED OR BUTTON YOU ADD MAKE SURE THE FXML HAS A fx:id YOU CAN SEE IN NAV
    // ON index.fxml HOW fx:id SHOULD BE USED.

    // Also remember to change what controller FXML looks at :) it's in the FXML file in the first VBOX element
    // I'm going to add more methods to the interface, also use the handleNavButtonClick to change pages
    // JUST PARSE THE BUTTON THAT WAS PRESSED

    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Add handlers for the buttons
        // Remember to use the correct method such as handleButtonClick()
        settings.setOnAction(event -> handleNavButtonClick("Settings", settings));
        home.setOnAction(event -> handleNavButtonClick("Settings", home));
        reminders.setOnAction(event -> handleNavButtonClick("Reminders", reminders));
        restrictions.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        information.setOnAction(event -> handleNavButtonClick("ADHD_Information", information));
        report.setOnAction(event -> handleNavButtonClick("Screen_Time", report));
        // EXAMPLE USE NOTICE I USE A DIFFERENT METHOD
        example.setOnAction(event -> handleButtonClick("example"));
    }


    // This is a method to handle buttonClicks,
    // Add a "switch" or "if" statement for different functions
    // YOU CAN ADJUST THIS AS NEEDED HENCE WHY I DID A OVERRIDE
    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        // Here's an example of reading the first name textbook
         System.out.println(readTextField(first_name));



    }

}
