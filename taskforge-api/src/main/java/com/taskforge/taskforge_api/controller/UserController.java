package com.taskforge.taskforge_api.controller;

import com.taskforge.taskforge_api.dto.UserResponse;
import com.taskforge.taskforge_api.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return repository.findAll().stream().map(UserResponse::from).toList();
    }
}