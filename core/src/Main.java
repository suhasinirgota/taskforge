import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Set up project"));
        tasks.add(new Task(2, "Write first class"));

        for (Task t : tasks) {
            System.out.println(t);
        }
    }
}