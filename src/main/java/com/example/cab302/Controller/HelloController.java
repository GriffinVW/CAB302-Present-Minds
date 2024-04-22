package com.example.cab302.Controller;

import com.example.cab302.Model.Event;
import com.example.cab302.Model.IEventDAO;
import com.example.cab302.Model.MockEventDAO;
import com.example.cab302.Model.TblEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

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