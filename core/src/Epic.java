public class Epic extends Task {
     public Epic(int id, String title) {
        super(id, title);
    }

    @Override
    public int estimatePoints() {
        return 13;
    }
    
}
