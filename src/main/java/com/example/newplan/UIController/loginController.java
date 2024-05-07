package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import com.example.newplan.*;
import com.example.newplan.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

    private UserDAO userDAO;

    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        //Initialize UserDAO instance
        userDAO = new UserDAO();

        // Handlers for when a button press is detected
        signup.setOnAction(event -> handleNavButtonClick("Create_Account", signup));
        login.setOnAction(actionEvent -> handleLoginButtonClick());
    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        // Here's an example of reading the first name textbook
        System.out.println(readTextField(login_username));
        System.out.println(readTextField(login_password));
    }

    private void handleLoginButtonClick() {
        String username = login_username.getText();
        String password = login_password.getText();
        User loggedInUser = null;

        boolean isAuthenticated = userDAO.authenticateUser(username, password);

        if(isAuthenticated) {
            System.out.println("User Authenticated!");
            handleNavButtonClick("index", login);
            loggedInUser = userDAO.getByUserName(username);
            //sessionManager.setUserId(loggedInUser.getId());
        } else {
            System.out.println("User Authentication Failed");
            login_username.setPromptText("Username/Password is Incorrect");
            login_username.setStyle("-fx-prompt-text-fill: #ff6666");
            login_password.setPromptText("Username/Password is Incorrect");
            login_password.setStyle("-fx-prompt-text-fill: #ff6666");
        }
    }
}
