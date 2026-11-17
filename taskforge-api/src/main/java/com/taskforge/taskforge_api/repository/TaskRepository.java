package com.taskforge.taskforge_api.repository;
import com.taskforge.taskforge_api.model.Status;
import com.taskforge.taskforge_api.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepository {
    private final Map<Integer, Task> tasks = new HashMap<>();

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