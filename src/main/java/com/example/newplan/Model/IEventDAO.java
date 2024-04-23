package com.example.newplan.Model;

import java.util.ArrayList;

public interface IEventDAO {

    public ArrayList<Event> getEventsForUser(int userId);
    //will contain overloads to allow for querying specific time periods.

    public void addEvent(Event event);
    public void addTblEvent(TblEvent tblevent);

    public void updateEvent(Event event);

    public void deleteEvent(int eventId);
    public ArrayList<TblEvent> getTblEventsForUser(int userId);
}

