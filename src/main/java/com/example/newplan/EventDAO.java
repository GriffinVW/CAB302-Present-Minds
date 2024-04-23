package com.example.newplan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private Connection connection;

    public EventDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        // Todo Later: Create a Statement to run the CREATE TABLE query
    }

    public void insert(Event event) {
        // Todo Later: Create a PreparedStatement to run the INSERT query
    }

    public void update(Event event) {
        // Todo Later: Create a PreparedStatement to run the UPDATE query
    }

    public void delete(int id) {
        // Todo Later: Create a PreparedStatement to run the DELETE query
    }

    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        // Todo Later: Create a Statement to run the SELECT * query
        // and populate the accounts list above
        return events;
    }

    public Event getById(int id) {
        // Todo Later: Create a PreparedStatement to run the conditional SELECT query
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }