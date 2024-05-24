package com.example.newplan.model;

import java.util.List;

    public class  EventsManager {
        private EventDAO eventDAO;

    public  EventsManager(EventDAO eventDAO){
        this.eventDAO = eventDAO;
    }
    public List<Event> getAllRestrictions() {

        if (SessionManager.getInstance().getUserId() != null) {
            return eventDAO.getAllRestrictions(SessionManager.getInstance().getUserId());
        }

        return null;
    }
}
