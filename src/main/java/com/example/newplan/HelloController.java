package com.example.newplan;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    private EventDAO eventDAO;
    public HelloController(){
        eventDAO = new EventDAO();
        eventDAO.createTable();
    }

    @FXML
    protected void onHelloButtonClick() {
        //ArrayList<TblEvent> events = eventDAO.getEventsForUser(1);
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JULY,3,16,30,0);
        Event event = new Event("Play Time", "Working today",cal,cal,true,true);
        eventDAO.insert(event,1);
        List<Event> events1 = eventDAO.getAllUser(1);
        welcomeText.setText(events1.get(0).getTitle());
    }

    private void handleButtonClick() {
        System.out.println("Button clicked!");
        // Add your functionality here
    }

}