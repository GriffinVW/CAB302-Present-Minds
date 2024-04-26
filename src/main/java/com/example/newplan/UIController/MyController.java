package com.example.newplan.UIController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class MyController implements Controller {

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

    // Label the text fields and checkboxes
    @FXML
    private TextField first_name;

    // JUST REMEMBER FOR EVERY TEXT FILED OR BUTTON YOU ADD MAKE SURE IN THE FXML IT HAS A fx:id YOU CAN SEE IN
    // settings.fxml I added one for the firstname input and all the NAV buttons,

    // ALSO remember to change what controller FXML looks at :) it's in the FXML file
    // I'm going to add more methods to the interface, also use the loadFXML() method but just comment it out
    // and i'll fix it when i update that method.



    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Add handlers for the buttons
        settings.setOnAction(event -> handleButtonClick("Settings"));
        home.setOnAction(event -> handleButtonClick("index"));
        reminders.setOnAction(event -> handleButtonClick("Reminders"));
        restrictions.setOnAction(event -> handleButtonClick("Restrictions"));
        information.setOnAction(event -> handleButtonClick("ADHD_Information"));
        report.setOnAction(event -> handleButtonClick("Screen_Time"));
    }

    // This is a method to handle buttonClicks, I am trying to make the nav work and switch FXML pages
    // But I haven't got it working so that's the code in there.

    // For signup pages just delete the current code in there.

    // Add a "switch" or "if" statement for different functions
    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        // Here's an example of reading the first name textbook
         System.out.println(readTextField(first_name));

        // Working on this RN for the nav, trying to make it switch pages

        //        Stage stage = (Stage) settings.getScene().getWindow();
        //        loadFXML("index.fxml", stage);
    }

}
