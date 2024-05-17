import com.example.newplan.UserDAO;
import com.example.newplan.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testUserDAO {
    private User user;
    private UserDAO userData;
    @BeforeEach
    public void setUp() {
        userData = new UserDAO();
        userData.createTable();
        user = new User("JD","John", "Doe", "john.doe@example.com");}
    @Test
    public void testGetById() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
        userData.close();
    }
//    @Test
//    public void testInsert() {
//        userData.insert(user);
//        User outUser = userData.getById(69);
//        assertEquals(user, outUser);
//        userData.close();
//    }
//    @Test
//    public void testDelete() {
//        userData.insert(user);
//        userData.delete(69);
//        User outUser = userData.getById(69);
//        assertNull(outUser);
//        userData.close();
//    }
//    @Test
//    public void testUpdate() {
//        userData.insert(user);
//        user.setFirstName("Jimmy");
//        userData.update(user);
//        User outUser = userData.getById(69);
//        assertEquals(outUser.getFirstName(), user.getFirstName());
//        userData.close();
//    }
//    @Test
//    public void testGetAll() {
//        User user2 = new User(25,"HP","Holly", "Perkins", "hol.perk@hotmail.com", "password!");
//        userData.insert(user);
//        userData.insert(user2);
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user2);
//        List<User> outUsers = new ArrayList<>();
//        outUsers = userData.getAll();
//        assertEquals(users, outUsers);
//        userData.close();
//    }
}
