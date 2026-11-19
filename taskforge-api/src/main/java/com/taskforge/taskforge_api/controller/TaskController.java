package com.taskforge.taskforge_api.controller;

import com.taskforge.taskforge_api.dto.CreateTaskRequest;
import com.taskforge.taskforge_api.dto.TaskResponse;
import com.taskforge.taskforge_api.exception.TaskNotFoundException;
import com.taskforge.taskforge_api.model.Bug;
import com.taskforge.taskforge_api.model.Epic;
import com.taskforge.taskforge_api.model.Story;
import com.taskforge.taskforge_api.model.Task;
import com.taskforge.taskforge_api.repository.TaskRepository;

import com.taskforge.taskforge_api.dto.CreateTaskRequest;
import com.taskforge.taskforge_api.model.Bug;
import com.taskforge.taskforge_api.model.Epic;
import com.taskforge.taskforge_api.model.Story;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
        int id = repository.nextId();
        Task task = switch (request.type()) {
            case "BUG" -> new Bug(id, request.title());
            case "STORY" -> new Story(id, request.title());
            default -> new Epic(id, request.title());
        };
        repository.save(task);
        return ResponseEntity
                .created(URI.create("/api/tasks/" + id))
                .body(TaskResponse.from(task));
    }
}