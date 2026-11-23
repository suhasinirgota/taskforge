package com.taskforge.taskforge_api.controller;

import com.taskforge.taskforge_api.dto.CreateTaskRequest;
import com.taskforge.taskforge_api.dto.TaskResponse;
import com.taskforge.taskforge_api.dto.UpdateTaskRequest;
import com.taskforge.taskforge_api.exception.InvalidTransitionException;
import com.taskforge.taskforge_api.exception.TaskNotFoundException;
import com.taskforge.taskforge_api.exception.UserNotFoundException;
import com.taskforge.taskforge_api.model.Bug;
import com.taskforge.taskforge_api.model.Epic;
import com.taskforge.taskforge_api.model.Story;
import com.taskforge.taskforge_api.model.Task;
import com.taskforge.taskforge_api.model.User;
import com.taskforge.taskforge_api.repository.TaskRepository;
import com.taskforge.taskforge_api.repository.UserRepository;
import com.taskforge.taskforge_api.service.TaskService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskforge.taskforge_api.dto.UpdateTaskRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final TaskService service;

    public TaskController(TaskRepository repository, UserRepository userRepository, TaskService service) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.service = service;
    }

    @GetMapping
    public List<TaskResponse> getAll() {
        return repository.findAllWithAssignee().stream()
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
        Task task = switch (request.type()) {
            case "BUG" -> new Bug(request.title());
            case "STORY" -> new Story(request.title());
            default -> new Epic(request.title());
        };
        Task saved = repository.save(task);
        return ResponseEntity
                .created(URI.create("/api/tasks/" + saved.getId()))
                .body(TaskResponse.from(saved));
    }

    @PutMapping("/{taskId}/assignee/{userId}")
    public TaskResponse assign(@PathVariable int taskId, @PathVariable int userId) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        task.assignTo(user);
        return TaskResponse.from(repository.save(task));
    }

    @PostMapping("/advance")
    public List<TaskResponse> advance(@RequestBody List<Integer> ids) throws InvalidTransitionException {
        return service.advanceAll(ids).stream().map(TaskResponse::from).toList();
    }
        @PatchMapping("/{id}/status")
    public TaskResponse advanceStatus(@PathVariable int id) throws InvalidTransitionException {
        return TaskResponse.from(service.advance(id));
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable int id, @Valid @RequestBody UpdateTaskRequest request) {
        return TaskResponse.from(service.rename(id, request.title()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}