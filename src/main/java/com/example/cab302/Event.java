package com.example.cab302;

import java.sql.*;
import java.util.Calendar;

public class Event {
    private int eventId;  //in the mock case eventId is actually the userID
    private String title;
    private String description;

    private Calendar startTime;
    private Calendar endTime;
    private boolean restrict;

    public Event(int eventId, String title, String description, Calendar startTime, Calendar endTime, boolean restrict) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.restrict = restrict;
    }

    // Getters and setters
    public int getId(){
        return eventId;
    }
    // toString() method, etc.
}