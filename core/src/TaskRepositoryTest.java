import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {

    @Test
    void savedTaskCanBeFoundById() {
        TaskRepository repo = new TaskRepository();
        Task t = new Story(1, "Test story");
        repo.save(t);
        assertTrue(repo.findById(1).isPresent());
        assertEquals(t, repo.findById(1).get());
    }

    @Test
    void findByIdReturnsEmptyWhenMissing() {
        TaskRepository repo = new TaskRepository();
        assertTrue(repo.findById(99).isEmpty());
    }

    @Test
    void findByStatusReturnsOnlyMatchingTasks() throws InvalidTransitionException {
        TaskRepository repo = new TaskRepository();
        Task a = new Story(1, "A");
        Task b = new Bug(2, "B");
        b.moveToNext();
        repo.save(a);
        repo.save(b);
        assertEquals(1, repo.findByStatus(Status.TODO).size());
        assertEquals(1, repo.findByStatus(Status.IN_PROGRESS).size());
    }
}