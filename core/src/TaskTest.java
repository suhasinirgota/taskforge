import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void newTaskStartsAsTodo() {
        Task t = new Story(1, "Test story");
        assertEquals(Status.TODO, t.getStatus());
    }

    @Test
    void moveToNextGoesFromTodoToInProgress() throws InvalidTransitionException {
        Task t = new Bug(2, "Test bug");
        t.moveToNext();
        assertEquals(Status.IN_PROGRESS, t.getStatus());
    }

    @Test
    void moveToNextAfterDoneThrowsException() throws InvalidTransitionException {
        Task t = new Epic(3, "Test epic");
        t.moveToNext();
        t.moveToNext();
        assertThrows(InvalidTransitionException.class, () -> t.moveToNext());
    }

    @Test
    void assignToSetsAssignee() {
        Task t = new Story(1, "Test story");
        assertNull(t.getAssignee());
        User suha = new User("Suha", "suha@example.com");
        t.assignTo(suha);
        assertEquals(suha, t.getAssignee());
}

}