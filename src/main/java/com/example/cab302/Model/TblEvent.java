package com.example.cab302.Model;

import java.sql.Timestamp;

public class TblEvent {

    private int eventId;  //in the mock case eventId is actually the userID
    private int userId;
    private String title;
    private String description;

    private Timestamp startTime;
    private Timestamp endTime;
    private boolean restrict;

    public TblEvent(int eventId, int userId, String title, String description, Timestamp startTime, Timestamp endTime, boolean restrict) {

        this.eventId = eventId;
        this.userId =userId;
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
    public String getTitle(){
        return title;
    }
    // toString() method, etc.

}
