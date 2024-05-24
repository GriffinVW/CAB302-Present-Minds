package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import com.example.newplan.model.Event;
import com.example.newplan.model.EventDAO;
import com.example.newplan.model.ProgramChecker;
import com.example.newplan.model.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.Button;
import javafx.stage.Stage;



// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class restrictionsController implements Controller {

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
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, String> columnC1;


    @FXML
    private TextField appName;

    @FXML
    private TextField fHh;
    @FXML
    private TextField fMm;
    @FXML
    private TextField fAm;
    @FXML
    private TextField fYy;
    @FXML
    private TextField fMmin;
    @FXML
    private TextField fDd;


    @FXML
    private TextField toHh;
    @FXML
    private TextField toMmin;
    @FXML
    private TextField toAm;
    @FXML
    private TextField toDd;
    @FXML
    private TextField toMm;
    @FXML
    private TextField toYy;

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

        newele.setOnAction(event -> handleButtonClick("new"));
        confirm.setOnAction(event -> confirmClk());
        getRestrictions();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                id = getId(newSelection);

                appName.setText(getTitle(newSelection));

            }
        });


    }

    public void getRestrictions() {
        EventDAO eventDAO = new EventDAO();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        cal2.set(2200, Calendar.DECEMBER, 24, 0, 0, 0);

        List<Event> events = eventDAO.getAllUserPeriodRestrictons(SessionManager.getInstance().getUserId(), cal1, cal2);
        for (Event event : events) {
            System.out.println(event.getDescription());
        }

        ObservableList<Event> data = FXCollections.observableArrayList(events);

        tableView.setItems(data);

        columnC1.setCellValueFactory(new PropertyValueFactory<>("title"));

        columnC1.setText("Events");



    }

    private void confirmClk() {
        EventDAO eventDAO = new EventDAO();
        String titleText = readTextField(appName);

        System.out.println(titleText);

        try {
            int toHour = Integer.parseInt(readTextField(toHh));
            int toMin = Integer.parseInt(readTextField(toMmin));

            int today = Integer.parseInt(readTextField(toDd));
            int toMonth = Integer.parseInt(readTextField(toMm));
            int toYear = Integer.parseInt(readTextField(toYy));

            int fHour = Integer.parseInt(readTextField(fHh));
            int fMin = Integer.parseInt(readTextField(fMmin));

            int fDay = Integer.parseInt(readTextField(fDd));
            int fMonth = Integer.parseInt(readTextField(fMm));
            int fYear = Integer.parseInt(readTextField(fYy));


            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.set(fYear, fMonth, fDay, fHour, fMin);
            cal2.set(toYear, toMonth, today, toHour, toMin);

            Event event = new Event(titleText, "", cal, cal2, true, false);
            eventDAO.insert(event, SessionManager.getInstance().getUserId());

            getRestrictions();
        } catch (NumberFormatException ex) {
            System.out.println("error");
        }
        
    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);
        getRestrictions();
        if (Objects.equals(buttonId, "confirm") ) {
            System.out.println(buttonId);
        }

    }

    private int getId(Event event) {
        return event.getEventId();
    }

    private String getTitle(Event event) {
        return event.getTitle();
    }

}
