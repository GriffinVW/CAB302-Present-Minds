package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


// haven't made the controllers for SignUp/Login yet

public interface Controller {
    void initialize();

    void handleButtonClick(String buttonId);

    default String readTextField(TextField textField) {
        return textField.getText();
    }


    default boolean isCheckBoxSelected(CheckBox checkBox) {
        return checkBox.isSelected();
    }

    default void handleNavButtonClick(String buttonId, Button refButton) {

        // Ok weird thing but the way I'm getting the scene we are on is using the getScene method on a button,
        // so if the button isn't on that page (e.g login and signup) you will need to change the getScene()
        // So its called on a different button

        switch (buttonId) {
            case "Settings":
            case "index":
            case "Reminders":
            case "Restrictions":
            case "ADHD_Information":
            case "login":
            case "Carer_Setup":
            case "Create_Account":
            case "Screen_Time":
                Stage stage = (Stage) refButton.getScene().getWindow();
                loadFXML(buttonId + ".fxml", HelloApplication.class, stage);
                break;
            default:
                // Prevents errors
                System.out.println("I don't have functionality for this button: " + buttonId);
        }
    }
    

    default void updateErrorText(Label errorLabel, String newText) {
        errorLabel.setText(newText);
    }


    // Loads a different FXML page
    default void loadFXML(String fxmlPath, Class<?> clazz, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(clazz.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
