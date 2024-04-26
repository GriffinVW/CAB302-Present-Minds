package com.example.newplan.model;

import java.util.ArrayList;

//deleted

public class MockUserData {

    public static final ArrayList<User> user_data = new ArrayList<>();
    private static int autoIncrementedId = 0;

    public MockUserData() {
        //addUserData(new User("John", "Doe", "JD@gmail.com", "1234"));
    }

    public void addUserData(User userdata) {
        userdata.setId(autoIncrementedId);
        autoIncrementedId++;
        user_data.add(userdata);
    }
}
