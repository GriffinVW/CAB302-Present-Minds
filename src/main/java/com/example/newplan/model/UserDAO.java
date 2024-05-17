package com.example.newplan.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
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
                            + "password VARCHAR NOT NULL, "
                            + "isCarer BOOLEAN DEFAULT FALSE, "
                            + "childFirstName VARCHAR, "
                            + "childLastName VARCHAR, "
                            + "canEditReminders BOOLEAN DEFAULT FALSE, "
                            + "canEditRestrictions BOOLEAN DEFAULT FALSE "
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle exception appropriately (e.g., log error, return default value, etc.)
        }
        return null; // Return null if hashing fails
    }

    public String retrievePassword(String userName) {
        try {
            PreparedStatement getPassword = connection.prepareStatement(
                    "SELECT password FROM userData WHERE userName = ?"
            );
            getPassword.setString(1, userName);
            ResultSet rs = getPassword.executeQuery();
            return rs.getString("password");

        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public void setPassword(String userName, String password) {
        try {
            PreparedStatement setPassword = connection.prepareStatement(
                    "UPDATE userData SET password = ? WHERE userName = ?"
            );
            setPassword.setString(1, hashPassword(password));
            setPassword.setString(2, userName);
            setPassword.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public boolean authenticateUser(String userName, String password) {
        // Retrieve the user's password hash from the database based on the username
        String storedPasswordHash = retrievePassword(userName);
        if (storedPasswordHash == null) {
            // User not found in the database
            return false;
        }

        // Hash the provided password using the same algorithm and salt used for hashing in the database
        String providedPasswordHash = hashPassword(password);

        // Compare the hashed passwords
        if (providedPasswordHash != null) {
            return providedPasswordHash.equals(storedPasswordHash);
        }

        return false;
    }

    public void insert(User user, String password) {
        try {
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT INTO userData (userName, firstName, lastName, email, password, isCarer, childFirstName, childLastName, canEditReminders, canEditRestrictions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            insertUser.setString(1, user.getUserName());
            insertUser.setString(2, user.getFirstName());
            insertUser.setString(3, user.getLastName());
            insertUser.setString(4, user.getEmail());
            insertUser.setString(5, password);
            insertUser.setBoolean(6, user.getIsCarer());
            insertUser.setString(7, user.getChildFirstName());
            insertUser.setString(8, user.getChildLastName());
            insertUser.setBoolean(9, user.getCanEditReminders());
            insertUser.setBoolean(10, user.getCanEditRestrictions());

            insertUser.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Checks if a username a new user is trying to take already exists or not in the database
     * @param username the username to check for in the database
     * @return true if username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        try {
            PreparedStatement checkUser = connection.prepareStatement(
                    "SELECT COUNT(*) FROM userData WHERE userName = ?"
            );
            checkUser.setString(1, username);
            ResultSet rs = checkUser.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // If the count is greater than 0 then the username exists
            }
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
        return false;
    }

    public void update(User user) {
        try {
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE userData SET userName = ?, firstName = ?, lastName = ?, email = ? WHERE id = ?"
            );
            updateAccount.setString(1, user.getUserName());
            updateAccount.setString(2, user.getFirstName());
            updateAccount.setString(3, user.getLastName());
            updateAccount.setString(4, user.getEmail());
            updateAccount.setInt(5, user.getId());
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

    public void clear() {
        try {
            PreparedStatement deleteUser = connection.prepareStatement("DROP TABLE userData");
            deleteUser.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
            try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT id, userName, firstName, lastName, email FROM userData");
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("userName"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email")
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
            PreparedStatement getUser = connection.prepareStatement("SELECT id, userName, firstName, lastName, email FROM userData WHERE id = ?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public User getByUserName(String username) {
        try {
            PreparedStatement getUserByUserName = connection.prepareStatement(
                    "SELECT id, userName, firstName, lastName, email FROM userData WHERE userName = ?"
            );
            getUserByUserName.setString(1, username);
            ResultSet rs = getUserByUserName.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email")
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