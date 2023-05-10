package com.example.vkproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskDto {
    private Long id;
    private boolean completed;
    private int pointsEarned;
    private LocalDateTime createdAt;
}
