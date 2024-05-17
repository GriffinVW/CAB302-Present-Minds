package com.example.newplan.UIController;

import com.example.newplan.model.SessionManager;
import com.example.newplan.model.User;
import com.example.newplan.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class settingsController implements Controller {

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
    private Button logout;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField email;
    @FXML
    private TextField user_name;
    @FXML
    private TextField change_password;
    @FXML
    private TextField re_enter_password;
    @FXML
    private Button confirm_button;
    @FXML
    private Button cancel_button;

    private UserDAO userDAO;

    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        userDAO = new UserDAO();

        // Add handlers for the buttons
        settings.setOnAction(event -> handleNavButtonClick("Settings", settings));
        home.setOnAction(event -> handleNavButtonClick("index", home));
        reminders.setOnAction(event -> handleNavButtonClick("Reminders", reminders));
        restrictions.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        information.setOnAction(event -> handleNavButtonClick("ADHD_Information", information));
        report.setOnAction(event -> handleNavButtonClick("Screen_Time", report));
        logout.setOnAction(event -> handleNavButtonClick("login", logout));

        // Fill fields in settings page with logged in user information
        User tempuser = userDAO.getById(SessionManager.getInstance().getUserId());
        first_name.setText(tempuser.getFirstName());
        last_name.setText(tempuser.getLastName());
        email.setText(tempuser.getEmail());
        user_name.setText(tempuser.getUserName());

        confirm_button.setOnAction(actionEvent -> handleConfirmButtonClick());
    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
    }

    private void handleConfirmButtonClick() {
        User tempuser = userDAO.getById(SessionManager.getInstance().getUserId());
        tempuser.setFirstName(first_name.getText());
        tempuser.setLastName(last_name.getText());
        tempuser.setEmail(email.getText());
        tempuser.setUserName(user_name.getText());
        userDAO.update(tempuser);

        String changePassword = change_password.getText();
        String reenterPassword = re_enter_password.getText();
        if (changePassword.equals(reenterPassword)){
            userDAO.setPassword(tempuser.getUserName(), reenterPassword);
            userDAO.update(tempuser);
        }
    }
}
