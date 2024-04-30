package com.example.newplan.UIController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class CreateAccountController implements Controller {

    // Label the buttons you need, below are the buttons for the nav, They shouldn't be included in the login or signup pages

    @FXML
    private Button create_account_back_button;
    @FXML
    private Button create_account_button;

    // Label the text fields and checkboxes
    @FXML
    private TextField create_account_first_name;

    @FXML
    private TextField create_account_last_name;

    @FXML
    private TextField create_account_email;

    @FXML
    private TextField create_account_username;

    @FXML
    private TextField create_account_password;

    @FXML
    private TextField create_account_reenter_password;

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
        create_account_button.setOnAction(event -> handleButtonClick("create_account_button"));
        // home.setOnAction(event -> handleButtonClick("index"));
        // reminders.setOnAction(event -> handleButtonClick("Reminders"));
        // restrictions.setOnAction(event -> handleButtonClick("Restrictions"));
        // information.setOnAction(event -> handleButtonClick("ADHD_Information"));
        // .setOnAction(event -> handleButtonClick("Screen_Time"));
    }

    // This is a method to handle buttonClicks, I am trying to make the nav work and switch FXML pages
    // But I haven't got it working so that's the code in there.

    // For signup pages just delete the current code in there.

    // Add a "switch" or "if" statement for different functions
    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        // Here's an example of reading the first name textbook
        System.out.println(readTextField(create_account_first_name));
        System.out.println(readTextField(create_account_last_name));
        System.out.println(readTextField(create_account_email));
        System.out.println(readTextField(create_account_username));
        System.out.println(readTextField(create_account_password));
        System.out.println(readTextField(create_account_reenter_password));

        // Working on this RN for the nav, trying to make it switch pages

        //        Stage stage = (Stage) settings.getScene().getWindow();
        //        loadFXML("index.fxml", stage);
    }

}
