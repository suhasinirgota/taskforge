package com.taskforge.taskforge_api.controller;

import com.taskforge.taskforge_api.dto.CreateTaskRequest;
import com.taskforge.taskforge_api.dto.TaskResponse;
import com.taskforge.taskforge_api.exception.TaskNotFoundException;
import com.taskforge.taskforge_api.exception.UserNotFoundException;
import com.taskforge.taskforge_api.model.Bug;
import com.taskforge.taskforge_api.model.Epic;
import com.taskforge.taskforge_api.model.Story;
import com.taskforge.taskforge_api.model.Task;
import com.taskforge.taskforge_api.repository.TaskRepository;
import com.taskforge.taskforge_api.repository.UserRepository;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskforge.taskforge_api.exception.UserNotFoundException;
import com.taskforge.taskforge_api.model.User;
import com.taskforge.taskforge_api.repository.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    

    public TaskController(TaskRepository repository, UserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
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
    
    // @PostMapping
    // public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
    //     int id = repository.nextId();
    //     Task task = switch (request.type()) {
    //         case "BUG" -> new Bug(id, request.title());
    //         case "STORY" -> new Story(id, request.title());
    //         default -> new Epic(id, request.title());
    //     };
    //     repository.save(task);
    //     return ResponseEntity
    //             .created(URI.create("/api/tasks/" + id))
    //             .body(TaskResponse.from(task));
    // }
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

}