import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Story(1, "Set up project"));
        tasks.add(new Bug(2, "Fix login crash"));
        tasks.add(new Epic(3, "User login system"));

        for (Task t : tasks) {
            System.out.println(t);
        }
        
        Task first = tasks.get(0);
try {
    first.moveToNext();
    first.moveToNext();
    first.moveToNext();
} catch (InvalidTransitionException e) {
    System.out.println("Error: " + e.getMessage());
}
System.out.println(first);
    }
}