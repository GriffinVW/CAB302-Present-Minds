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

import java.time.Year;
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
    private Button delete;



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
    private TextField fMin;
    @FXML
    private TextField fDd;

    @FXML
    private Label errorLabel;


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
    Event selected;

    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        updateErrorText(errorLabel, " ");
        System.out.println("Initialization complete");

        // Add handlers for the buttons
        settings.setOnAction(event -> handleNavButtonClick("Settings", settings));
        home.setOnAction(event -> handleNavButtonClick("index", home));
        reminders.setOnAction(event -> handleNavButtonClick("Reminders", reminders));
        restrictions.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        information.setOnAction(event -> handleNavButtonClick("ADHD_Information", information));
        report.setOnAction(event -> handleNavButtonClick("Screen_Time", report));
        logout.setOnAction(event -> handleNavButtonClick("login", logout));
        delete.setOnAction(event -> delete());

        newele.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        confirm.setOnAction(event -> confirmClk());
        getRestrictions();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                id = getId(newSelection);
                selected = newSelection;

                appName.setText(getTitle(newSelection));

                fMm.setText(String.valueOf(newSelection.getStartTime().get(Calendar.MONTH)));
                fHh.setText(String.valueOf(newSelection.getStartTime().get(Calendar.DAY_OF_MONTH)));
                fMin.setText(String.valueOf(newSelection.getStartTime().get(Calendar.MINUTE)));
                fDd.setText(String.valueOf(newSelection.getStartTime().get(Calendar.DAY_OF_MONTH)));
                fYy.setText(String.valueOf(newSelection.getStartTime().get(Calendar.YEAR)));

                toMm.setText(String.valueOf(newSelection.getEndTime().get(Calendar.MONTH)));
                toHh.setText(String.valueOf(newSelection.getEndTime().get(Calendar.HOUR_OF_DAY)));
                toMmin.setText(String.valueOf(newSelection.getEndTime().get(Calendar.MINUTE)));
                toDd.setText(String.valueOf(newSelection.getEndTime().get(Calendar.DAY_OF_MONTH)));
                toYy.setText(String.valueOf(newSelection.getEndTime().get(Calendar.YEAR)));

                System.out.println(newSelection.getStartTime());

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
        updateErrorText(errorLabel, " ");
        EventDAO eventDAO = new EventDAO();
        String titleText = readTextField(appName);

        System.out.println(titleText);

        try {
            int toHour = Integer.parseInt(readTextField(toHh));
            int toMin = Integer.parseInt(readTextField(toMmin));

            int today = Integer.parseInt(readTextField(toDd));
            int toMonthInt = Integer.parseInt(readTextField(toMm));
            int toYear = Integer.parseInt(readTextField(toYy));

            int fHour = Integer.parseInt(readTextField(fHh));
            int fMinute = Integer.parseInt(readTextField(fMin));

            int fDay = Integer.parseInt(readTextField(fDd));
            int fMonthInt = Integer.parseInt(readTextField(fMm));
            int fYear = Integer.parseInt(readTextField(fYy));

            if (fYear > 2100 || toYear > 2100) {
                System.out.println("This date is too far away");
                updateErrorText(errorLabel, "This date is too far away");
                return;
            } else if (fYear < Year.now().getValue() || toYear < Year.now().getValue()) {
                System.out.println("This date is in the past");
                updateErrorText(errorLabel, "This date is in the past, please choose a future date");
                return;
            } else if (fMonthInt < 1 || fMonthInt > 12 || fHour > 23 || toMonthInt < 1 || toMonthInt > 12 || toHour > 23 || fMinute > 59 || toMin > 59 || today < 1 || today > 31 || fDay < 1 || fDay > 31) {
                System.out.println("Impossible date");
                updateErrorText(errorLabel, "Impossible date");
                return;
            }

            int fMonth;
            int toMonth;

            switch (fMonthInt) {
                case 1: fMonth = Calendar.JANUARY; break;
                case 2: fMonth = Calendar.FEBRUARY; break;
                case 3: fMonth = Calendar.MARCH; break;
                case 4: fMonth = Calendar.APRIL; break;
                case 5: fMonth = Calendar.MAY; break;
                case 6: fMonth = Calendar.JUNE; break;
                case 7: fMonth = Calendar.JULY; break;
                case 8: fMonth = Calendar.AUGUST; break;
                case 9: fMonth = Calendar.SEPTEMBER; break;
                case 10: fMonth = Calendar.OCTOBER; break;
                case 11: fMonth = Calendar.NOVEMBER; break;
                case 12: fMonth = Calendar.DECEMBER; break;
                default:
                    System.out.println("Invalid month");
                    updateErrorText(errorLabel, "Invalid month");
                    return;
            }

            switch (toMonthInt) {
                case 1: toMonth = Calendar.JANUARY; break;
                case 2: toMonth = Calendar.FEBRUARY; break;
                case 3: toMonth = Calendar.MARCH; break;
                case 4: toMonth = Calendar.APRIL; break;
                case 5: toMonth = Calendar.MAY; break;
                case 6: toMonth = Calendar.JUNE; break;
                case 7: toMonth = Calendar.JULY; break;
                case 8: toMonth = Calendar.AUGUST; break;
                case 9: toMonth = Calendar.SEPTEMBER; break;
                case 10: toMonth = Calendar.OCTOBER; break;
                case 11: toMonth = Calendar.NOVEMBER; break;
                case 12: toMonth = Calendar.DECEMBER; break;
                default:
                    System.out.println("Invalid month");
                    updateErrorText(errorLabel, "Invalid month");
                    return;
            }

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
            cal2.set(2200, Calendar.DECEMBER, 24, 0, 0, 0);

            List<Event> events = eventDAO.getAllUserPeriodRestrictons(SessionManager.getInstance().getUserId(), cal1, cal2);
            for (Event event : events) {
                System.out.println(event.getTitle());

                if (Objects.equals(event.getTitle(), titleText)) {
                    System.out.println("Needs unique name");
                    updateErrorText(errorLabel, "Needs unique name");
                    return;
                }
            }

            cal1.set(fYear, fMonth, fDay, fHour, fMinute);
            cal2.set(toYear, toMonth, today, toHour, toMin);

            Event event = new Event(titleText, "", cal1, cal2, true, false);
            eventDAO.insert(event, SessionManager.getInstance().getUserId());

            getRestrictions();
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Number");
            updateErrorText(errorLabel, "Invalid Number");
        }


    }

    public void delete() {
        EventDAO eventDAO = new EventDAO();
        if (selected != null) {
            System.out.println(selected.getTitle() + " removed");
            System.out.println(selected.getEventId() + " removed");
            eventDAO.delete(selected.getTitle());
            getRestrictions();
        } else {
            System.out.println("Please Select Event");
            updateErrorText(errorLabel, "Please Select Event");
//            updateErrorText(errorLabel, "Please Select Event");
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
