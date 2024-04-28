package com.example.newplan;

import java.sql.Timestamp;
import java.util.Calendar;

public class Event {
    private int eventId;  //in the mock case eventId is actually the userID
    private String title;
    private String description;

    private Calendar startTime;
    private Calendar endTime;
    private boolean restrict;
    private boolean reminder;


    public Event(int eventId, String title, String description, Calendar startTime, Calendar endTime, boolean restrict, boolean reminder) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.restrict = restrict;
        this.reminder = reminder;
    }
    public Event(int eventId, String title, String description, String startTime, String endTime, String restrict, String reminder) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        //following require conversions
        Timestamp timestamp = Timestamp.valueOf(startTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        this.startTime = calendar;
        timestamp = Timestamp.valueOf(endTime);
        calendar.setTimeInMillis(timestamp.getTime());
        this.endTime = calendar;
        this.restrict = restrict.equals("1");
        this.reminder = reminder.equals("1");
    }
    public Event(String title, String description, Calendar startTime, Calendar endTime, boolean restrict, boolean reminder) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.restrict = restrict;
        this.reminder = reminder;
    }
    // Getters and setters
    // toString() method, etc.
    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    // converts Calender to a timeStamp then into a string.

    public String getStartTimeStr() {
        Timestamp timestamp1 = new Timestamp(startTime.getTimeInMillis());
        return (timestamp1.toString());
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public String getEndTimeStr() {
    Timestamp timestamp1 = new Timestamp(endTime.getTimeInMillis());
    return (timestamp1.toString());
    }

    public boolean  getRestrict() {
        return restrict;
    }
    public String  getRestrictBit() {
        if(restrict){return "1";}
        else {return "0";}

    }
    public boolean getReminder() {
        return reminder;
    }
    public String getReminderBit() {
        if(reminder){return "1";}
        else {return "0";}
    }
}