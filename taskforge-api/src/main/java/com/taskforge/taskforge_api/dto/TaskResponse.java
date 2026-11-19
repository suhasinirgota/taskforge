package com.taskforge.taskforge_api.dto;

import com.taskforge.taskforge_api.model.Status;
import com.taskforge.taskforge_api.model.Task;

public record TaskResponse(int id, String title, Status status, int points, String type) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.estimatePoints(),
                task.getClass().getSimpleName());
    }
}