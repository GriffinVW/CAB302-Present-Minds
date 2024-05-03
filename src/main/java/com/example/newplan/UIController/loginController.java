package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Login page controller, handles frontend user inputs
 */
public class loginController implements Controller {

    // Page buttons
    @FXML
    private Button signup;
    @FXML
    private Button login;

    // Page text-fields
    @FXML
    private TextField login_username;
    @FXML
    private TextField login_password;

    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Handlers for when a button press is detected
        signup.setOnAction(event -> handleNavButtonClick("Create_Account", signup));
        login.setOnAction(event -> handleButtonClick("login"));
    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        // Here's an example of reading the first name textbook
        System.out.println(readTextField(login_username));
        System.out.println(readTextField(login_password));
    }

}
