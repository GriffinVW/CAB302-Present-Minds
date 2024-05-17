
//package com.example.newplan;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//
//public class HelloController {
//    @FXML
//    private Button settings;
//    @FXML
//    private Button home;
//    @FXML
//    private Button reminders;
//    @FXML
//    private Button restrictions;
//    @FXML
//    private Button information;
//    @FXML
//    private Button report;
//
//
////    Make Conterller interface
//
//    @FXML
//    private void initialize() {
//        System.out.println("Test");
//        settings.setOnAction(event -> handleButtonClick());
//        home.setOnAction(event -> handleButtonClick());
//        reminders.setOnAction(event -> handleButtonClick());
//        restrictions.setOnAction(event -> handleButtonClick());
//        information.setOnAction(event -> handleButtonClick());
//        report.setOnAction(event -> handleButtonClick());
//    }
//
//    private void handleButtonClick() {
//        System.out.println("Button clicked!");
//        // Add your functionality here
//    }
//
//}

package com.example.newplan;

import com.example.newplan.model.EventsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    private EventDAO eventDAO;
    private EventsManager eventsManager;
    public HelloController(){
        eventDAO = new EventDAO();
        eventsManager = new EventsManager(eventDAO);
        eventDAO.createTable();

    }

    @FXML
    protected void onHelloButtonClick() {
        //ArrayList<TblEvent> events = eventDAO.getEventsForUser(1);
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(2024, Calendar.MAY,10,10,30,0);
        cal2.set(2024, Calendar.MAY,10,16,30,0);
        Event event = new Event("Play Time", "Working today",cal,cal2,true,true);
        eventDAO.delete(1);
        eventDAO.insert(event,1);
        List<Event> events1 = eventDAO.getAllUser(1);
        welcomeText.setText(events1.get(0).getTitle());
    }
}

