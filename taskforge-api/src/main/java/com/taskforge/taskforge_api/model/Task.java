package com.taskforge.taskforge_api.model;
import com.taskforge.taskforge_api.exception.InvalidTransitionException;
public abstract class Task {
    private int id;
    private String title;
    private Status status;
    private User assignee;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
    }
    public void assignTo(User user) {
        this.assignee = user;
    }

    public abstract int estimatePoints();

    public int getId() { return id; }
    public String getTitle() { return title; }
    public Status getStatus() { return status; }
    public User getAssignee() { return assignee;}

    public void moveToNext() throws InvalidTransitionException {
    if (status == Status.TODO) {
        status = Status.IN_PROGRESS;
    } else if (status == Status.IN_PROGRESS) {
        status = Status.DONE;
    } else {
        throw new InvalidTransitionException("Task " + id + " is already DONE");
    }
}

    @Override
    public String toString() {
        return id + " | " + title + " | " + status + " | " + estimatePoints() + " pts";
    }
}