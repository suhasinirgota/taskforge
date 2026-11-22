package com.taskforge.taskforge_api.dto;

import com.taskforge.taskforge_api.model.User;

public record UserResponse(int id, String name, String email) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}