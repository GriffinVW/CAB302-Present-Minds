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

    // Add a UserDAO instance to accountController for user insertion logic
    private UserDAO userDAO = new UserDAO();

    // Page buttons
    @FXML
    private Button create_account_back_button;
    @FXML
    private Button create_account_button;

    // Text-fields on signup page
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

    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Handlers for when a button press is detected
        create_account_button.setOnAction(event -> handleButtonClick("create_account_button"));
        create_account_back_button.setOnAction(event -> handleNavButtonClick("login", create_account_back_button));
    }

    /**
     * Handles user input into the database via UserDAO
     * Verifies password is consistent, checks username uniqueness and hashes passwords
     * @param buttonId the id of the button that was clicked
     */
    @Override
    public void handleButtonClick(String buttonId) {
        // getting text-field input on button click
        if ("create_account_button".equals(buttonId)) {
            String firstName = create_account_first_name.getText();
            String lastName = create_account_last_name.getText();
            String email = create_account_email.getText();
            String username = create_account_username.getText();
            String password = create_account_password.getText();
            String reenterPassword = create_account_reenter_password.getText();

            // verify password
            if (!password.equals(reenterPassword)) {
                System.out.println("Passwords do not match");
                return;
            }

            // Username availability
            if (userDAO.usernameExists(username)) {
                System.out.println("Username is taken, please choose another.");
                return;
            }

            // Password hashing
            String hashedPassword = userDAO.hashPassword(password);
            if (hashedPassword == null) {
                System.out.println("Failed to hash password");
                return;
            }

            // user insertion
            User newUser = new User(username, firstName, lastName, email, password);
            userDAO.insert(newUser);
            System.out.println("User created successfully!");
        }
    }

}
