package com.taskforge.taskforge_api.repository;
import com.taskforge.taskforge_api.model.Status;
import com.taskforge.taskforge_api.model.Task;

import org.springframework.stereotype.Repository;
import com.taskforge.taskforge_api.model.Bug;
import com.taskforge.taskforge_api.model.Epic;
import com.taskforge.taskforge_api.model.Story;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

@Repository 
public class TaskRepository {
    private final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(3);

public TaskRepository() {
        save(new Story(1, "Set up project"));
        save(new Bug(2, "Fix login crash"));
        save(new Epic(3, "User login system"));
    }

    public int nextId() {
    return idCounter.incrementAndGet();
    }

    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public List<Task> findByStatus(Status status) {
        return tasks.values().stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }
}