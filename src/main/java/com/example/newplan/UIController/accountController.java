package com.example.newplan.UIController;

import com.example.newplan.UserDAO;
import com.example.newplan.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Primary controller for user creation in the application.
 * Handles user input on create account page and stores it in the database.
 */
public class accountController implements Controller {

    private UserDAO userDAO = new UserDAO();

    // Label the buttons you need, below are the buttons for the nav, They shouldn't be included in the login or signup pages

    @FXML
    private Button create_account_back_button;
    @FXML
    private Button create_account_button;

    // Page textfields
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
    }

    // This is a method to handle buttonClicks, I am trying to make the nav work and switch FXML pages
    // But I haven't got it working so that's the code in there.

    // For signup pages just delete the current code in there.

    // Add a "switch" or "if" statement for different functions
    @Override
    public void handleButtonClick(String buttonId) {
        if ("create_account_button".equals(buttonId)) {
            String firstName = create_account_first_name.getText();
            String lastName = create_account_last_name.getText();
            String email = create_account_email.getText();
            String username = create_account_username.getText();
            String password = create_account_password.getText();
            String reenterPassword = create_account_reenter_password.getText();

            if (!password.equals(reenterPassword)) {
                System.out.println("Passwords do not match");
                return;
            }

            if (userDAO.usernameExists(username)) {
                System.out.println("Username is taken, please choose another.");
                return;
            }

            User newUser = new User(username, firstName, lastName, email, password);
            userDAO.insert(newUser);
            System.out.println("User created successfully!");
        }
    }

}
