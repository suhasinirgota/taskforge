public abstract class Task {
    private int id;
    private String title;
    private String status;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = "TODO";
    }

    public abstract int estimatePoints();

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + status + " | " + estimatePoints() + " pts";
    }
}