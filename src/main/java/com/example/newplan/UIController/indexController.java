package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import com.example.newplan.model.AppTrackerDAO;
import com.example.newplan.model.EventDAO;
import com.example.newplan.model.EventsManager;
import com.example.newplan.model.ProgramChecker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class indexController implements Controller {

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

    // Label the text fields and checkboxes


    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Add handlers for the button

        settings.setOnAction(event -> handleNavButtonClick("Settings", settings));
        home.setOnAction(event -> handleNavButtonClick("index", home));
        reminders.setOnAction(event -> handleNavButtonClick("Reminders", reminders));
        restrictions.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        information.setOnAction(event -> handleNavButtonClick("ADHD_Information", information));
        report.setOnAction(event -> handleNavButtonClick("Screen_Time", report));
        logout.setOnAction(event -> handleNavButtonClick("login", logout));



    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);


    }

}
