package com.example.newplan.model;

import java.util.List;

public class EventsManager {
    private EventDAO eventDAO;

    public  EventsManager(EventDAO eventDAO){
        this.eventDAO = eventDAO;
    }
    public List<Event> getAllRestrictions(){
        return eventDAO.getAllRestrictions(1);
    }
}
