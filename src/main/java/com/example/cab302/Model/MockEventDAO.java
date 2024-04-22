package com.example.cab302.Model;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
public class MockEventDAO implements IEventDAO{

    public static final ArrayList<TblEvent> tblEvents = new ArrayList<>();
    private static int autoIncrementedId = 0;

    public ArrayList<Event> getEventsForUser(int userId){
        return null;
    }

    @Override
    public void addEvent(Event event) {

    }



    public MockEventDAO(){
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(2024, Calendar.JULY,3,16,30,0);
        cal2.set(2024, Calendar.JULY,3,18,30,0);
        Timestamp timestamp1 = new Timestamp(cal.getTimeInMillis());
        Timestamp timestamp2 = new Timestamp(cal2.getTimeInMillis());
        addTblEvent(new TblEvent(1,1, "Work", "Working toay",timestamp1,timestamp2,true));
//        addEvent(new Event(1, "Fun", "Working toay", new Timestamp(cal.getTimeInMillis()),new Timestamp(cal.getTimeInMillis()),true));
//        addEvent(new Event(2, "COde", "Working toay", new Timestamp(cal.getTimeInMillis()),new Timestamp(cal.getTimeInMillis()),true));
    //in the mock case eventId is actually the userID
    }

    @Override
    public ArrayList<TblEvent> getTblEventsForUser(int userId) {
        ArrayList<TblEvent> userEvents = new ArrayList<TblEvent>();
        for (TblEvent event : tblEvents) {
            if (event.getId() == userId) {
                userEvents.add(event);
            }
            return userEvents;
        }
        return null;
    }

@Override
    public void addTblEvent(TblEvent tblEvent) {
        tblEvents.add(tblEvent);
    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public void deleteEvent(int eventId) {

    }
}
