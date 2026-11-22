package com.taskforge.taskforge_api.model;

import com.taskforge.taskforge_api.exception.InvalidTransitionException;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
// import jakarta.persistence.Transient;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "tasks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type")
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;

    // @Transient
    // private User assignee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    protected Task() {
    }

    protected Task(String title) {
        this.title = title;
    }

    public abstract int estimatePoints();

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public Status getStatus() { return status; }

    public void assignTo(User user) {
        this.assignee = user;
    }

    public User getAssignee() {
        return assignee;
    }

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
// package com.taskforge.taskforge_api.model;
// import com.taskforge.taskforge_api.exception.InvalidTransitionException;
// public abstract class Task {
//     private int id;
//     private String title;
//     private Status status;
//     private User assignee;

//     public Task(int id, String title) {
//         this.id = id;
//         this.title = title;
//         this.status = Status.TODO;
//     }
//     public void assignTo(User user) {
//         this.assignee = user;
//     }

//     public abstract int estimatePoints();

//     public int getId() { return id; }
//     public String getTitle() { return title; }
//     public Status getStatus() { return status; }
//     public User getAssignee() { return assignee;}

//     public void moveToNext() throws InvalidTransitionException {
//     if (status == Status.TODO) {
//         status = Status.IN_PROGRESS;
//     } else if (status == Status.IN_PROGRESS) {
//         status = Status.DONE;
//     } else {
//         throw new InvalidTransitionException("Task " + id + " is already DONE");
//     }
// }

//     @Override
//     public String toString() {
//         return id + " | " + title + " | " + status + " | " + estimatePoints() + " pts";
//     }
// }