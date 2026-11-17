import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Story(1, "Set up project"));
        tasks.add(new Bug(2, "Fix login crash"));
        tasks.add(new Epic(3, "User login system"));
        tasks.add(new Story(4, "Design database"));
        tasks.add(new Bug(5, "Fix typo on homepage"));
        tasks.add(new Story(6, "Write README"));


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
//
Map<Integer, Task> taskById = new HashMap<>();
for (Task t : tasks) {
    taskById.put(t.getId(), t);
}

System.out.println("Task 4 is: " + taskById.get(4));
try {
    tasks.get(3).moveToNext();
    tasks.get(4).moveToNext();
    tasks.get(4).moveToNext();
} catch (InvalidTransitionException e) {
    System.out.println("Error: " + e.getMessage());
}
System.out.println("--- TODO tasks ---");
tasks.stream()
     .filter(t -> t.getStatus() == Status.TODO)
     .forEach(System.out::println);

long doneCount = tasks.stream()
                      .filter(t -> t.getStatus() == Status.DONE)
                      .count();
System.out.println("Done count: " + doneCount);
    }
}