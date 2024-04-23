package com.example.newplan.model;
/**
 * A simple com.example.newplan.model class representing user's data with a id, firstname, lastname, email, and password.
 */
public class User {
    /**
     * Constructs a new UserData with the specified id, first name, last name, email, and password.
     * @param id The id of the user
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param email The email of the user
     * @param password The phone number of the user
     */
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }
}
