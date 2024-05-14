package com.example.newplan.UIController;

import com.example.newplan.Event;
import com.example.newplan.EventDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;


// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class remindersController implements Controller {

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
    private Button newele;
    @FXML
    private Button confirm;

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private TableView<Event> tableView;

    @FXML
    private TableColumn<Event, String> columnC1;

    @FXML
    private Label errorLabel;

    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        System.out.println("Initialization complete");

        // Add handlers for the buttons
        settings.setOnAction(event -> handleNavButtonClick("Settings", settings));
        home.setOnAction(event -> handleNavButtonClick("index", home));
        reminders.setOnAction(event -> handleNavButtonClick("Reminders", reminders));
        restrictions.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        information.setOnAction(event -> handleNavButtonClick("ADHD_Information", information));
        report.setOnAction(event -> handleNavButtonClick("Screen_Time", report));
        logout.setOnAction(event -> handleNavButtonClick("login", logout));
        newele.setOnAction(event -> handleButtonClick("newele"));
        confirm.setOnAction(event -> handleButtonClick("confirm"));

        getReminders();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(getTitle(newSelection) + " title");
                System.out.println(getDescription(newSelection) + " description");

                title.setText(getTitle(newSelection));
                description.setText(getDescription(newSelection));
            }
        });
    }

    public void getReminders() {
        EventDAO eventDAO = new EventDAO();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
        cal2.set(2024, Calendar.DECEMBER, 24, 0, 0, 0);

        List<Event> events = eventDAO.getAllUserPeriodReminders(1, cal1, cal2);
        for (Event event : events) {
            System.out.println(event.getDescription());
        }

        ObservableList<Event> data = FXCollections.observableArrayList(events);

        tableView.setItems(data);

        columnC1.setCellValueFactory(new PropertyValueFactory<>("title"));

        columnC1.setText("Events");
    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        if (Objects.equals(buttonId, "newele")) {
            getReminders();
        } else {
            if (readTextField(title).isEmpty() || readTextArea(description).isEmpty()) {
                System.out.println("Please fill in all the fields");
                updateErrorText(errorLabel, "Please fill in all the fields");
                return;
            }

            EventDAO eventDAO = new EventDAO();
            eventDAO.createTable();

            System.out.println(readTextField(title));

            Calendar cal = Calendar.getInstance();
            cal.set(2024, Calendar.JULY, 3, 16, 30, 0);
            Event event = new Event(readTextField(title), readTextArea(description), cal, cal, false, true);
            eventDAO.insert(event, 1);
        }
    }

    private void printSelectedRow(Event event) {
        String title = "Title: " + event.getTitle();
        String description = "Description: " + event.getDescription();

        System.out.println(title);
        System.out.println(description);


    }

    private String getDescription(Event event) {
        return event.getDescription();
    }

    private String getTitle(Event event) {
        return event.getTitle();
    }
}
