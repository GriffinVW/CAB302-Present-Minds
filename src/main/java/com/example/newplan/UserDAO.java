package com.example.newplan;

import com.example.newplan.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS userData ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userName VARCHAR NOT NULL, "
                            + "firstName VARCHAR NOT NULL, "
                            + "lastName VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL, "
                            + "password VARCHAR NOT NULL "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(User user) {
        try {
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT INTO userData (userName, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?)"
            );
            insertUser.setString(1, user.getUserName());
            insertUser.setString(2, user.getFirstName());
            insertUser.setString(3, user.getLastName());
            insertUser.setString(4, user.getEmail());
            insertUser.setString(5, user.getPassword());

            insertUser.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public boolean usernameExists(String username) {
        try {
            PreparedStatement checkUser = connection.prepareStatement(
                    "SELECT COUNT(*) FROM userData WHERE userName = ?"
            );
            checkUser.setString(1, username);
            ResultSet rs = checkUser.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // If count is greater than 0, username exists
            }
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
        return false;
    }

    public void update(User user) {
        try {
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE userData SET userName = ?, firstName = ?, lastName = ?, email = ?, password = ? WHERE id = ?"
            );
            updateAccount.setString(1, user.getUserName());
            updateAccount.setString(2, user.getFirstName());
            updateAccount.setString(3, user.getLastName());
            updateAccount.setString(4, user.getEmail());
            updateAccount.setString(5, user.getPassword());
            updateAccount.setInt(6, user.getId());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM userData WHERE id = ?");
            deleteUser.setInt(1, id);
            deleteUser.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
            try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM userData");
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("userName"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }

    public User getById(int id) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM userData WHERE id = ?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
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