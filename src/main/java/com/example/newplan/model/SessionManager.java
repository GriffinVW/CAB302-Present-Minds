package com.example.newplan.model;

import java.util.UUID;

public class SessionManager {
    private static SessionManager instance;
    private String sessionId;
    private Integer userId;

    private SessionManager() {
        // Private constructor to prevent instantiation
        sessionId = generateSessionId();
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    private String generateSessionId() {
        // Generate a unique session ID using UUID
        return UUID.randomUUID().toString();
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        String message = "User with ID: " + this.userId + " logged in to session with ID: " + this.sessionId;
        System.out.println(message);
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }
}

