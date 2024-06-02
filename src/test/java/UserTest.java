import com.example.newplan.model.UserDAO;
import com.example.newplan.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(99,"JD","John", "Doe", "john.doe@example.com");
    }

    @Test
    public void testId() {
        user.setId(100);
        assertEquals(100, user.getId());
    }

    @Test
    public void testUsername() {
        user.setUserName("NewName");
        assertEquals("NewName", user.getUserName());
    }

    @Test
    public void testCarer() {
        user.setIsCarer(true);
        assertTrue(user.getIsCarer());
    }

    @Test
    public void testToString() {
        String toString = user.toString();
        assertEquals("User{id=99, userName='JD', firstName='John', lastName='Doe', email='john.doe@example.com'}", toString);
    }
}
