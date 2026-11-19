package com.taskforge.taskforge_api.controller;

import com.taskforge.taskforge_api.dto.TaskResponse;
import com.taskforge.taskforge_api.exception.TaskNotFoundException;
import com.taskforge.taskforge_api.model.Task;
import com.taskforge.taskforge_api.repository.TaskRepository;
import java.util.Comparator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TaskResponse> getAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(Task::getId))
                .map(TaskResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable int id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return TaskResponse.from(task);
    }
}