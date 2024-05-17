package com.example.newplan.UIController;

import com.example.newplan.model.UserDAO;
import com.example.newplan.model.User;
import com.example.newplan.model.TempUserStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.CheckBox;

import javafx.scene.control.Label;
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

    // Checkboxes on signup page
    @FXML
    private CheckBox carer_account_checkbox;

    @FXML
    private Label errorLabel;

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
            String firstName = create_account_first_name.getText().trim();
            String lastName = create_account_last_name.getText().trim();
            String email = create_account_email.getText().trim();
            String username = create_account_username.getText().trim();
            String password = create_account_password.getText().trim();
            String reenterPassword = create_account_reenter_password.getText().trim();

            // prevent users from inserting empty fields into the DB
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || reenterPassword.isEmpty()) {
                System.out.println("Please fill in all the fields");
                return;
            }

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


            // User insertion
            User newUser = new User(username, firstName, lastName, email);
            // if the checkbox is ticked
            if (isCheckBoxSelected(carer_account_checkbox)) {
                // store user data temporarily
                TempUserStorage.getInstance().storeUser(newUser);
                // store hashed password temporarily
                TempUserStorage.getInstance().storeHashedPassword(hashedPassword);
                // switch to carer sign up page
                handleNavButtonClick("Carer_Setup", create_account_button);
            } else {
                // otherwise, proceed with normal user insertion
                userDAO.insert(newUser, hashedPassword);
                updateErrorText(errorLabel, "User created successfully!");
                System.out.println("User created successfully!");
                handleNavButtonClick("login", create_account_button);
            }

        }
    }
}
