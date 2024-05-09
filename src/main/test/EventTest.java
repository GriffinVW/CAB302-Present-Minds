import com.example.newplan.Event;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Timestamp;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event event;
    @BeforeEach
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JULY, 3, 16, 30, 0);
        event = new Event(1, "Play Time", "Working today", cal, cal, true, true);

    }

    @Test
    public void testEventId() {
        assertEquals(1, event.getEventId());
    }
    @Test
    public void testGetTitle() {
        assertEquals("Play Time", event.getTitle());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Working today", event.getDescription());
    }

    @Test
    public void testGetStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JULY, 3, 16, 30, 0);
        event.getStartTime().set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals(cal.getTimeInMillis(), event.getStartTime().getTimeInMillis());
    }

    @Test
    public void testGetEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JULY, 3, 16, 30, 0);
        event.getEndTime().set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals(cal.getTimeInMillis(), event.getEndTime().getTimeInMillis());
    }
    @Test
    public void testGetRestrict() {
        assertEquals(true, event.getRestrict());
    }

    @Test
    public void testGetReminder() {
        assertEquals(true, event.getReminder());
    }
    @Test
    public void testSetTitle() {
        event.setTitle("New Title");
        assertEquals("New Title", event.getTitle());
    }

    @Test
    public void testSetDescription() {
        event.setDescription("New Description");
        assertEquals("New Description", event.getDescription());
    }

    @Test
    public void testSetStartTime() {
        Calendar newCal = Calendar.getInstance();
        newCal.set(2024, Calendar.AUGUST, 15, 10, 0, 0);
        event.setStartTime(newCal);
        assertEquals(newCal.getTimeInMillis(), event.getStartTime().getTimeInMillis());
    }

    @Test
    public void testSetEndTime() {
        Calendar newCal = Calendar.getInstance();
        newCal.set(2024, Calendar.AUGUST, 15, 12, 0, 0);
        event.setEndTime(newCal);
        assertEquals(newCal.getTimeInMillis(), event.getEndTime().getTimeInMillis());
    }

    @Test
    public void testSetRestrict() {
        event.setRestrict(false);
        assertEquals(false, event.getRestrict());
    }

    @Test
    public void testSetReminder() {
        event.setReminder(false);
        assertEquals(false, event.getReminder());
    }
    @Test
    public void testGetRestrictBit() {
        assertEquals("1", event.getRestrictBit());
    }
    @Test
    public void testGetRestrictBitFalse() {
        event.setRestrict(false);
        assertEquals("0", event.getRestrictBit());

    }

    @Test
    public void testGetReminderBitFalse() {
        event.setReminder(false);
        assertEquals("0", event.getReminderBit());
    }
    @Test
    public void testGetStartTimeStr() {
        // Expected timestamp string based on the startTime set in the setup
        String expectedTimestampStr = "2024-07-03 16:30:00.0";
        event.getStartTime().set(Calendar.MILLISECOND, 0);
        // Call the method to get the timestamp string
        String actualTimestampStr = event.getStartTimeStr();

        // Assert that the returned string matches the expected string
        assertEquals(expectedTimestampStr, actualTimestampStr);
    }

    @Test
    public void testGetEndTimeStr() {
        // Expected timestamp string based on the endTime set in the setup
        String expectedTimestampStr = "2024-07-03 16:30:00.0";
        event.getEndTime().set(Calendar.MILLISECOND, 0);
        // Call the method to get the timestamp string
        String actualTimestampStr = event.getEndTimeStr();

        // Assert that the returned string matches the expected string
        assertEquals(expectedTimestampStr, actualTimestampStr);
    }
    @Test
    public void testConstructorWithStringParameters() {
        int eventId = 2;
        String title = "Test Event";
        String description = "Test Description";
        String startTimeStr = "2024-04-30 12:00:00";
        String endTimeStr = "2024-04-30 13:00:00";
        String restrictStr = "1";
        String reminderStr = "1";
        Event event = new Event(eventId, title, description, startTimeStr, endTimeStr, restrictStr, reminderStr);

        assertEquals(eventId, event.getEventId());
        assertEquals(title, event.getTitle());
        assertEquals(description, event.getDescription());
        // Convert string to calendar and compare
        Calendar startTime = Calendar.getInstance();
        startTime.setTimeInMillis(Timestamp.valueOf(startTimeStr).getTime());
        assertEquals(startTime.getTimeInMillis(), event.getStartTime().getTimeInMillis());
        // Same for endTime
        Calendar endTime = Calendar.getInstance();
        endTime.setTimeInMillis(Timestamp.valueOf(endTimeStr).getTime());
        assertEquals(endTime.getTimeInMillis(), event.getEndTime().getTimeInMillis());
        assertEquals(true, event.getRestrict());
        assertEquals(true, event.getReminder());
    }
    @Test
    public void testConstructorWithBasicParameters() {
        String title = "Test Event";
        String description = "Test Description";
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        boolean restrict = true;
        boolean reminder = true;

        Event event = new Event(title, description, startTime, endTime, restrict, reminder);

        assertEquals(title, event.getTitle());
        assertEquals(description, event.getDescription());
        assertEquals(startTime, event.getStartTime());
        assertEquals(endTime, event.getEndTime());
        assertEquals(restrict, event.getRestrict());
        assertEquals(reminder, event.getReminder());
    }

}
