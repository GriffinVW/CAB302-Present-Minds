package com.example.newplan.UIController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public interface Controller {
    void initialize();

    void handleButtonClick(String buttonId);


    default String readTextField(TextField textField) {
        return textField.getText();
    }


    default boolean isCheckBoxSelected(CheckBox checkBox) {
        return checkBox.isSelected();
    }


    // Loads a different FXML page, currently not working (working it as you read this)
    default void loadFXML(String fxmlPath, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
