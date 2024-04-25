package com.example.newplan;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    private IEventDAO eventDAO;
    public HelloController(){
        eventDAO = new MockEventDAO();
    }

    @FXML
    protected void onHelloButtonClick() {
        ArrayList<TblEvent> events = eventDAO.getTblEventsForUser(1);
        welcomeText.setText(events.get(0).getTitle());
    }
}