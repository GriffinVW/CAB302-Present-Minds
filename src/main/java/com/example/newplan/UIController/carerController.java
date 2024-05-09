package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

// This is an example controller,
public class carerController implements Controller {

    // Carer setup page buttons
    @FXML
    private Button carer_setup_login_button;
    @FXML
    private Button carer_setup_back_button;

    // Carer setup page textfields
    @FXML
    private TextField child_first_name;
    @FXML
    private TextField child_last_name;

    // Carer setup page checkboxes
    @FXML
    private CheckBox edit_restrictions;
    @FXML
    private CheckBox edit_reminders;

    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Add handlers for the buttons
        // Remember to use the correct method such as handleButtonClick()
        carer_setup_back_button.setOnAction(event -> handleNavButtonClick("login", carer_setup_back_button));
    }


    // This is a method to handle buttonClicks,
    // Add a "switch" or "if" statement for different functions
    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        // Here's an example of reading the first name textfield
        System.out.println(readTextField(child_first_name));
        // Here's an example of reading the last name textfield
        System.out.println(readTextField(child_last_name));



    }

}
