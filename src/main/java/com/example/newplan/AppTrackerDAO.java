package com.example.newplan;

import com.example.newplan.model.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AppTrackerDAO {
    private Connection connection;

    public AppTrackerDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS appTracker ("
                            + "appName VARCHAR NOT NULL, "
                            + "date VARCHAR NOT NULL, "
                            + "timeLog INTEGER NOT NULL "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public boolean appOpenedToday(String appName) {
        try {
            String currentDate = String.valueOf(java.time.LocalDateTime.now()).substring(0,10);
            PreparedStatement checkApp = connection.prepareStatement(
                    "SELECT COUNT(*) FROM appTracker WHERE appName = ? and date = ?"
            );
            checkApp.setString(1, appName);
            checkApp.setString(2, currentDate);
            ResultSet rs = checkApp.executeQuery();

            if (rs.next()) {
                // if (rs.getInt(1) > 0) System.out.println(appName + " has already been opened on " + currentDate);
                // else System.out.println(appName + " has not been opened on " + currentDate);
                return rs.getInt(1) > 0;  // If the count is greater than 0 then the app exists today
            }
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
        return false;
    }

    public int getTime(String appName) {
        try {
                if (appOpenedToday(appName)) {
                    PreparedStatement getTime = connection.prepareStatement(
                            "SELECT timeLog FROM appTracker WHERE appName = ? and date = ?"
                    );
                    getTime.setString(1, appName);
                    getTime.setString(2, String.valueOf(java.time.LocalDateTime.now()).substring(0, 10));
                    ResultSet rs = getTime.executeQuery();

                    return rs.getInt("timeLog");
                }
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
        return 0;
    }



    public void insert(String appName) {
        try {
            String currentDate = String.valueOf(java.time.LocalDateTime.now()).substring(0,10);
            PreparedStatement insertApp = connection.prepareStatement(
                    "INSERT INTO appTracker (appName, date, timeLog) VALUES (?, ?, ?)"
            );
            insertApp.setString(1, appName);
            insertApp.setString(2, currentDate);
            insertApp.setInt(3, 5);
            insertApp.execute();
//            System.out.println(
//                    appName + " has been added to the app tracker for " + currentDate
//            );
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
    }

    public void update(String appName) {
        try {
            if (appOpenedToday(appName)) {
                int appTime = getTime(appName);
                int newTime = appTime + 5;
                String currentDate = String.valueOf(java.time.LocalDateTime.now()).substring(0,10);
                PreparedStatement updateTimeLog = connection.prepareStatement(
                        "UPDATE appTracker SET timeLog = ? WHERE appName = ? and date = ?"
                );
                updateTimeLog.setInt(1, newTime);
                updateTimeLog.setString(2, appName);
                updateTimeLog.setString(3, currentDate);
                updateTimeLog.execute();
//                System.out.println(
//                        appName + " has been updated from " + appTime + "min to " + newTime  +  "min for " + currentDate
//                );
            } else {
                insert(appName);
            }
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}