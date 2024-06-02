package com.example.newplan.model;

import java.util.Calendar;
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
    public List<Event> getRemindersNow() {
        Integer id = SessionManager.getInstance().getUserId();
        EventDAO eventDAO = new EventDAO();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MINUTE, 5);
        if (id != null) {
            List<Event> events = eventDAO.getAllUserPeriodReminders(id, cal1, cal2);
            return events;
        }
        return null;
    }
}

