package com.example.newplan.UIController;

import com.example.newplan.model.UserDAO;
import com.example.newplan.model.User;
import com.example.newplan.model.TempUserStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


/**
 * Secondary controller for user creation in the application.
 * Handles user input from the account setup page and on the carer setup page and stores it in the database.
 */
public class carerController implements Controller {

    // Add a UserDAO instance to carerController for user insertion logic
    private UserDAO userDAO = new UserDAO();

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

        // handlers for button presses
        carer_setup_login_button.setOnAction(event -> handleButtonClick("carer_setup_login_button"));
        carer_setup_back_button.setOnAction(event -> {
            // if user presses the back button, erase all temporary data and head back to the login page
            TempUserStorage.getInstance().clearAll();
            System.out.println("Temporary user data erased!");
            handleNavButtonClick("login", carer_setup_back_button);
        });
    }


    /**
     * Handles user input into the database via UserDAO and TempUserStorage.
     * Retrieves the temporarily stored user data in TempUserStorage and checks if the object is null.
     * Reads text-box inputs and checkboxes from carer page.
     * Adds the data to tempUser and then inserts.
     * @param buttonId the id of the button that was clicked.
     */
    @Override
    public void handleButtonClick(String buttonId) {
        if ("carer_setup_login_button".equals(buttonId)) {
            // retrieve and check temporary user data
            User tempUser = TempUserStorage.getInstance().getUser();
            if (tempUser == null) {
                System.out.println("Error, no user data found!");
                return;
            }

            // retrieve and check hashed password
            String hashedPassword = TempUserStorage.getInstance().getHashedPassword();
            if (hashedPassword == null) {
                System.out.println("Error, couldn't find hashed password!");
                return;
            }

            // read text-boxes and checkboxes on carer_setup page
            String childFirstName = child_first_name.getText().trim();
            String childLastName = child_last_name.getText().trim();
            boolean canEditReminders = edit_reminders.isSelected();
            boolean canEditRestrictions = edit_restrictions.isSelected();

            // prevent users from inserting empty fields into the DB
            if (childFirstName.isEmpty() || childLastName.isEmpty()) {
                System.out.println("Please fill in all the fields");
                return;
            }

            // add carer data to tempUser
            tempUser.setChildFirstName(childFirstName);
            tempUser.setChildLastName(childLastName);
            tempUser.setCanEditReminders(canEditReminders);
            tempUser.setCanEditRestrictions(canEditRestrictions);
            tempUser.setIsCarer(true);

            // insert completed user data into the database
            userDAO.insert(tempUser, hashedPassword);
            System.out.println("User created successfully!");

            // Clear the temporary storage
            TempUserStorage.getInstance().clearAll();

            // Redirect to login page
            handleNavButtonClick("login", carer_setup_login_button);
        }
    }
}
