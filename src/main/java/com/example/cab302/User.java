package com.example.cab302;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private List<Event> events;

    public User(int userId) {
        this.userId = userId;
        this.events = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public List<Event> getEvents() {
        return events;
    }
    // Other methods for user-specific operations on events
}

//CREATE TABLE Users (
//        userId INT AUTO_INCREMENT PRIMARY KEY,
//        username VARCHAR(255) NOT NULL,
//password VARCHAR(255) NOT NULL
//);
//
//CREATE TABLE Events (
//        eventId INT AUTO_INCREMENT PRIMARY KEY,
//        userId INT,
//        title VARCHAR(255) NOT NULL,
//description TEXT,
//startTime DATETIME,
//endTime DATETIME,
//FOREIGN KEY (userId) REFERENCES Users(userId)
//        );
