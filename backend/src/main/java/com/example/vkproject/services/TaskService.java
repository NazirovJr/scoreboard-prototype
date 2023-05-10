package com.example.vkproject.services;

import com.example.vkproject.dto.TaskDto;
import com.example.vkproject.exceptions.CategoryException;
import com.example.vkproject.exceptions.TaskNotFoundException;
import com.example.vkproject.models.Tasks;
import com.example.vkproject.models.enumarate.CategoryEnum;
import com.example.vkproject.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Tasks> getTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }

        return taskRepository.findById(id);
//                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Tasks createTask(TaskDto taskDto) {
        if (!CategoryEnum.contains(taskDto.getCategory())) {
            throw new CategoryException("Not Founded category = " + taskDto.getCategory());
        }

        Tasks tasks = new Tasks();
        tasks.setName(taskDto.getName());
        tasks.setCategory(taskDto.getCategory());
        return taskRepository.save(tasks);
    }

    public void deleteTask(Long id) {
        Tasks task = getTaskById(id).get();
        taskRepository.delete(task);
    }

    public Tasks updateTask(Long taskId, TaskDto taskDto) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
        Optional<Tasks> tasks = taskRepository.findById(taskId);
        tasks.get().setName(taskDto.getName());
        tasks.get().setCategory(taskDto.getCategory());

        return taskRepository.save(tasks.get());
    }
}
