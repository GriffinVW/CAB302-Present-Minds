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

    public void update(Event event) {
        try {
            PreparedStatement updateEvent = connection.prepareStatement(
                    "UPDATE event SET  title = ?, description = ?, startTime = ?, endTime = ?, restrict = ?, reminder = ? WHERE eventId = ?"
            );
            //updateEvent.setString(1, String.valueOf(userId)); //Not allowing userId to be changed
            updateEvent.setString(1, event.getTitle());
            updateEvent.setString(2, event.getDescription());
            updateEvent.setString(3, event.getStartTimeStr());
            updateEvent.setString(4, event.getEndTimeStr());
            updateEvent.setString(5, event.getRestrictBit());
            updateEvent.setString(6, event.getReminderBit());
            updateEvent.setInt(7, event.getEventId());
            updateEvent.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    public void delete(int eventId) {
        try {
            PreparedStatement deleteEvent = connection.prepareStatement("DELETE FROM event WHERE eventId = ?");
            deleteEvent.setInt(1, eventId);
            deleteEvent.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
/*
       //Not Relevent!
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

    public Event getEventId(int eventId) {
        try {
            PreparedStatement getEventId = connection.prepareStatement("SELECT userId, title, description, startTime, endTime, restrict, reminder FROM event WHERE eventId = ?");
            getEventId.setInt(1, eventId);
            ResultSet rs = getEventId.executeQuery();
            if (rs.next()) {
                return new Event(
                        rs.getInt("userId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("startTime"),
                        rs.getString("endTime"),
                        rs.getString("restrict"),
                        rs.getString("reminder")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
    public  List<Event> getAllUser(int userId) {
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
    public List<Event> getAllUserPeriod(int userId, Calendar minDate, Calendar maxDate) {
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement EventsUserPeriod = connection.prepareStatement("SELECT userId, title, description, startTime, endTime, restrict, reminder FROM event WHERE userId = ? AND startTime BETWEEN ? AND ?");

            // Get the time in milliseconds from the Calendar object
            long timeInMillis = minDate.getTimeInMillis();
            // Create a Timestamp object using the time in milliseconds
            Timestamp timestamp = new Timestamp(timeInMillis);
            // Convert the Timestamp object to a String
            String minDateStr= timestamp.toString();
            long timeInMillis2 = maxDate.getTimeInMillis();
            // Create a Timestamp object using the time in milliseconds
            Timestamp timestamp2 = new Timestamp(timeInMillis2);
            // Convert the Timestamp object to a String
            String maxDateStr= timestamp2.toString();
            EventsUserPeriod.setInt(1, userId);
            EventsUserPeriod.setString(2, minDateStr);
            EventsUserPeriod.setString(3, maxDateStr);
            ResultSet rs = EventsUserPeriod.executeQuery();
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