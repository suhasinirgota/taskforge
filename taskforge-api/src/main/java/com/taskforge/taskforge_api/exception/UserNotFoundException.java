package com.taskforge.taskforge_api.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User " + id + " not found");
    }
}