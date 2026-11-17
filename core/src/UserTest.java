import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void usersWithSameEmailAreEqual() {
        User a = new User("Suha", "suha@example.com");
        User b = new User("Suha N", "suha@example.com");
        assertEquals(a, b);
    }

    @Test
    void usersWithDifferentEmailAreNotEqual() {
        User a = new User("Suha", "suha@example.com");
        User b = new User("Suha", "other@example.com");
        assertNotEquals(a, b);
    }

    @Test
    void setKeepsOnlyOneOfEqualUsers() {
        Set<User> users = new HashSet<>();
        users.add(new User("Suha", "suha@example.com"));
        users.add(new User("Suha N", "suha@example.com"));
        assertEquals(1, users.size());
    }
}