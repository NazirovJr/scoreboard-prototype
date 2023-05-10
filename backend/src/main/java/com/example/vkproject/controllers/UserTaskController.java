package com.example.vkproject.controllers;


import com.example.vkproject.dto.UserTaskDto;
import com.example.vkproject.models.UserTask;
import com.example.vkproject.services.UserTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user_tasks")
public class UserTaskController {

    private final UserTaskService userTaskService;

    public UserTaskController(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserTaskDto>> getUserTasks(@PathVariable Long userId) {
        List<UserTaskDto> userTasks = userTaskService.getUserTasksByUserId(userId);
        return ResponseEntity.ok(userTasks);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserTask> addUserTask(@PathVariable Long userId, @RequestBody UserTaskDto userTaskDto) {
        UserTask createdUserTask = userTaskService.addUserTask(userId, userTaskDto);
        return ResponseEntity.created(URI.create("/api/users/" + userId + "/tasks/" + createdUserTask.getId()))
                .body(createdUserTask);
    }

    @GetMapping("/{userTaskId}")
    public ResponseEntity<UserTask> getUserTaskById(@PathVariable Long userTaskId) {
        UserTask userTask = userTaskService.getUserTaskResultById(userTaskId);
        return ResponseEntity.ok(userTask);
    }

    @PutMapping("/{userId}/solve/{userTaskId}")
    public ResponseEntity<UserTask> updateUserTask(@PathVariable Long userId, @PathVariable Long userTaskId,
                                                      @RequestBody UserTaskDto userTaskDto) {
        UserTask updatedUserTask = userTaskService.updateUserTask(userId, userTaskId, userTaskDto);
        return ResponseEntity.ok(updatedUserTask);
    }

    @DeleteMapping("/{userTaskId}")
    public ResponseEntity<Void> deleteUserTask(@PathVariable Long userTaskId) {
        userTaskService.deleteUserTaskResult(userTaskId);
        return ResponseEntity.noContent().build();
    }
}

