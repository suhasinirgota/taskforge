package com.taskforge.taskforge_api.service;

import com.taskforge.taskforge_api.exception.InvalidTransitionException;
import com.taskforge.taskforge_api.exception.TaskNotFoundException;
import com.taskforge.taskforge_api.model.Task;
import com.taskforge.taskforge_api.repository.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    @Transactional(rollbackFor = InvalidTransitionException.class)
    public List<Task> advanceAll(List<Integer> ids) throws InvalidTransitionException {
        List<Task> result = new ArrayList<>();
        for (Integer id : ids) {
            Task task = repository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));
            task.moveToNext();
            result.add(repository.save(task));
        }
        return result;
    }
}