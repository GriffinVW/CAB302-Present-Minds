package com.example.newplan.model;
/**
 * A simple com.example.newplan.model class representing user's data with a id, firstname, lastname, email, and password.
 */
public class User {
    /**
     * Constructs a new UserData with the specified id, first name, last name, email, and password.
     * @param id The id of the user
     * @param userName The id of the user
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param email The email of the user
     * @param password The phone number of the user
     */
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    //private String password;
    private boolean isCarer;
    private String childFirstName;
    private String childLastName;
    private boolean canEditReminders;
    private boolean canEditRestrictions;

    public User(int id, String userName, String firstName, String lastName, String email) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean getIsCarer() {return isCarer;}

    public void setIsCarer(boolean isCarer) {this.isCarer = isCarer;}

    public String getChildFirstName() {return childFirstName;}

    public void setChildFirstName(String childFirstName) {this.childFirstName = childFirstName;}

    public String getChildLastName() {return childLastName;}

    public void setChildLastName(String childLastName) {this.childLastName = childLastName;}

    public boolean getCanEditReminders() {return canEditReminders;}

    public void setCanEditReminders(boolean canEditReminders) {this.canEditReminders = canEditReminders;}

    public boolean getCanEditRestrictions() {return canEditRestrictions;}

    public void setCanEditRestrictions(boolean canEditRestrictions) {this.canEditRestrictions = canEditRestrictions;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
