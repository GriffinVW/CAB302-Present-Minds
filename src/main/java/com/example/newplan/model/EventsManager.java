package com.example.newplan.model;

import java.util.Calendar;
import java.util.List;

public class EventsManager {
    private EventDAO eventDAO;

    public  EventsManager(EventDAO eventDAO){
        this.eventDAO = eventDAO;
    }
    public List<Event> getAllRestrictions(){
        return eventDAO.getAllRestrictions(1);
    }
    public List<Event> getRemindersNow() {
        EventDAO eventDAO = new EventDAO();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MINUTE, 5);
        List<Event> events = eventDAO.getAllUserPeriodReminders(1, cal1, cal2);
        return events;
    }
}

