package com.example.newplan.model;

/**
 * A class for temporarily storing user data that was inputted on the account signup page
 * Stores already hashed password
 */
public class TempUserStorage {
    private static TempUserStorage instance;
    private User temporaryUser;
    private String temporaryHashedPassword;

    private TempUserStorage() {
    }

    public static TempUserStorage getInstance() {
        if (instance == null) {
            instance = new TempUserStorage();
        }
        return instance;
    }

    // retrieve temporary user data
    public void storeUser(User user) {
        this.temporaryUser = user;
    }

    // get temporary user data
    public User getUser() {
        return this.temporaryUser;
    }

    public void clearUser() {
        this.temporaryUser = null;
    }

    // Retrieve and store the hashed password temporarily
    public void storeHashedPassword(String hashedPassword) {
        this.temporaryHashedPassword = hashedPassword;
    }

    // Get the hashed password
    public String getHashedPassword() {
        return temporaryHashedPassword;
    }

    public void clearPassword() {
        this.temporaryHashedPassword = null;
    }

    // Erase the temporary storage
    public void clearAll() {
        clearUser();
        clearPassword();
    }
}
