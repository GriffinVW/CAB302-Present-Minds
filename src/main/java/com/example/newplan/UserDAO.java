package com.example.newplan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        // Todo Later: Create a Statement to run the CREATE TABLE query
    }

    public void insert(User user) {
        // Todo Later: Create a PreparedStatement to run the INSERT query
    }

    public void update(User user) {
        // Todo Later: Create a PreparedStatement to run the UPDATE query
    }

    public void delete(int id) {
        // Todo Later: Create a PreparedStatement to run the DELETE query
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>(User);
        // Todo Later: Create a Statement to run the SELECT * query
        // and populate the accounts list above
        return users;
    }

    public User getById(int id) {
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
}