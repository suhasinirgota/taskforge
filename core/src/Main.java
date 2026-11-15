import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Story(1, "Set up project"));
        tasks.add(new Bug(2, "Fix login crash"));

        for (Task t : tasks) {
            System.out.println(t);
        }
    }
}