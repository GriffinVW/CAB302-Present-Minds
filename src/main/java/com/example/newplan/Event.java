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
        Timestamp timestamp1 = Timestamp.valueOf(startTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp1.getTime());
        this.startTime = calendar;
        Calendar calendar1 = Calendar.getInstance();
        Timestamp timestamp2 = Timestamp.valueOf(endTime);
        calendar1.setTimeInMillis(timestamp2.getTime());
        this.endTime = calendar1;
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
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}
    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar cal){this.startTime = cal;}

    // converts Calender to a timeStamp then into a string.

    public String getStartTimeStr() {
        Timestamp timestamp1 = new Timestamp(startTime.getTimeInMillis());
        return (timestamp1.toString());
    }


    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar cal){this.endTime = cal;}


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
    public void setRestrict(Boolean restrict){this.restrict = restrict;}
    public boolean getReminder() {
        return reminder;
    }
    public void setReminder(Boolean reminder){this.reminder = reminder;}
    public String getReminderBit() {
        if(reminder){return "1";}
        else {return "0";}
    }
}