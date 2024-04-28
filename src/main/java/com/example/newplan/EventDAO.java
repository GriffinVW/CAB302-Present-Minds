package com.example.newplan;

import com.example.newplan.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// This will be connected to Event class in calendersystem branch
public class EventDAO {
    private Connection connection;

    public EventDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        // Todo Later: Create a Statement to run the CREATE TABLE query
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS event ("
                            + "eventId INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userId INTEGER NOT NULL, "
                            + "title VARCHAR NOT NULL, "
                            + "description VARCHAR NOT NULL, "
                            + "startTime DATETIME NOT NULL, "
                            + "endTime DATETIME NOT NULL, "
                            + "restrict BIT NOT NULL,"
                            + "reminder BIT NOT NULL,"
                            +"FOREIGN KEY (userId) REFERENCES Users(userId)"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void insert(Event event, int userId) {
        try {
            PreparedStatement insertEvent = connection.prepareStatement(
                    "INSERT INTO event (userId, title, description, startTime, endTime, restrict, reminder) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            insertEvent.setString(1, String.valueOf(userId));
            insertEvent.setString(2, event.getTitle());
            insertEvent.setString(3, event.getDescription());
            insertEvent.setString(4, event.getStartTimeStr());
            insertEvent.setString(5, event.getEndTimeStr());
            insertEvent.setString(6, event.getRestrictBit());
            insertEvent.setString(7, event.getReminderBit());
            insertEvent.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    /*
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
    */
    public List<Event> getAllUser(int userId) {
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement getEventsUser = connection.prepareStatement("SELECT userId, title, description, startTime, endTime, restrict, reminder FROM event WHERE userId = ?");
            getEventsUser.setInt(1, userId);
            ResultSet rs = getEventsUser.executeQuery();
            while (rs.next()) {
                events.add(
                        new Event(
                                rs.getInt("userId"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getString("startTime"),
                                rs.getString("endTime"),
                                rs.getString("restrict"),
                                rs.getString("reminder")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return events;
    }
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}