package com.example.newplan.UIController;

import com.example.newplan.Event;
import com.example.newplan.EventDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.Year;
import java.util.*;


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
    private Button delete;

    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private TextField day;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    @FXML
    private TextField min;
    @FXML
    private TextField second;
    @FXML
    private TextField hour;
    @FXML
    private TableView<Event> tableView;

    @FXML
    private TableColumn<Event, String> columnC1;

    @FXML
    private Label errorLabel;


    int id = 0;

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
        delete.setOnAction(event -> handleButtonClick("delete"));

        getReminders();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(getId(newSelection) + " Id");
                id = getId(newSelection);
                System.out.println(getTitle(newSelection) + " title");
                System.out.println(getDescription(newSelection) + " description");
                System.out.println(getStartTime(newSelection).getInstance());

                title.setText(getTitle(newSelection));
                description.setText(getDescription(newSelection));

                day.setText(String.valueOf(getStartTime(newSelection).getInstance().get(Calendar.DAY_OF_MONTH)));
                month.setText(String.valueOf(getStartTime(newSelection).getInstance().get(Calendar.MONTH) - 1));
                year.setText(String.valueOf(getStartTime(newSelection).getInstance().get(Calendar.YEAR)));

                min.setText(String.valueOf(getStartTime(newSelection).getInstance().get(Calendar.MINUTE)));
                second.setText(String.valueOf(getStartTime(newSelection).getInstance().get(Calendar.SECOND)));
                hour.setText(String.valueOf(getStartTime(newSelection).getInstance().get(Calendar.HOUR_OF_DAY)));

            }
        });
    }

    public void getReminders() {
        EventDAO eventDAO = new EventDAO();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
        cal2.set(2200, Calendar.DECEMBER, 24, 0, 0, 0);

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
        } else if (Objects.equals(buttonId, "confirm")) {
            if (readTextField(title).isEmpty() || readTextArea(description).isEmpty()) {
                System.out.println("Please fill in all the fields");
                updateErrorText(errorLabel, "Please fill in all the fields");
                return;
            }

            EventDAO eventDAO = new EventDAO();
            eventDAO.createTable();

            String titleText = readTextField(title);
            String descritptionText = readTextArea(description);

            try {
                int dayInt = Integer.parseInt(readTextField(day));
                int monthInt = Integer.parseInt(readTextField(month));
                int yearInt = Integer.parseInt(readTextField(year));
                int minInt = Integer.parseInt(readTextField(min));
                int secondInt = Integer.parseInt(readTextField(second));
                int hourInt = Integer.parseInt(readTextField(hour));

                if (yearInt > 2100) {
                    System.out.println("This date is to far away");
                    updateErrorText(errorLabel, "This date is to far away");
                    return;
                } else if (yearInt < Year.now().getValue()) {
                    System.out.println("This date is in the past");
                    updateErrorText(errorLabel, "This date is in the past, please choose a future date");
                    return;
                } else if (monthInt > 12 || hourInt > 24 || secondInt > 60) {
                    System.out.println("Impossible date");
                    updateErrorText(errorLabel, "Impossible date");
                    return;
                }

                Calendar cal = Calendar.getInstance();
                cal.set(yearInt, monthInt, dayInt, hourInt, minInt, secondInt);
                Event event = new Event(titleText, descritptionText, cal, cal, false, true);
                eventDAO.insert(event, 1);

                System.out.println("day " + dayInt + " month " + monthInt + " year " + yearInt + " min " + minInt + " second " + secondInt + " hour " + hourInt);
                getReminders();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid integer");
                updateErrorText(errorLabel, "Invalid integer");
            }
        } else if (Objects.equals(buttonId, "delete")) {
            if (id != 0) {
                System.out.println(id + "removed");
                EventDAO eventDAO = new EventDAO();
                eventDAO.delete(id);
            } else {
                System.out.println("Please Select Event");
                updateErrorText(errorLabel, "Please Select Event");
            }
        }
    }


    private String getDescription(Event event) {
        return event.getDescription();
    }

    private String getTitle(Event event) {
        return event.getTitle();
    }

    private Calendar getStartTime(Event event) {
        return event.getStartTime();
    }

    private int getId(Event event) {
        return event.getEventId();
    }

}
