package com.taskforge.taskforge_api.repository;

import com.taskforge.taskforge_api.model.Status;
import com.taskforge.taskforge_api.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(Status status);

    @Query("select t from Task t left join fetch t.assignee")
    List<Task> findAllWithAssignee();
}