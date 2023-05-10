package com.example.vkproject.exceptions;

public class UserTaskResultNotFoundException extends RuntimeException {
    public UserTaskResultNotFoundException(Long userId, Long taskId) {
        super(String.format("User task result for user with id %d and task with id %d not found", userId, taskId));
    }
}
