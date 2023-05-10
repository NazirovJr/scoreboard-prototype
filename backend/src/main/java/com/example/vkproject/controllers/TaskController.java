package com.example.vkproject.controllers;


import com.example.vkproject.dto.TaskDto;
import com.example.vkproject.models.Tasks;
import com.example.vkproject.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Long taskId) {
        Tasks taskDto = taskService.getTaskById(taskId).get();
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping("/create")
    public ResponseEntity<Tasks> createTask(@RequestBody TaskDto taskDto) {
        Tasks createdTaskDto = taskService.createTask(taskDto);
        return ResponseEntity.created(URI.create("/tasks/" + createdTaskDto.getId())).body(createdTaskDto);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        Tasks updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updatedTaskDto);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
