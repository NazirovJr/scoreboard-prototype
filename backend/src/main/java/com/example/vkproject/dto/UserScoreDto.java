package com.example.vkproject.dto;

import lombok.Data;

@Data
public class UserScoreDto {

    private Long userId;
    private String username;
    private int totalPointsEarned;
    private int totalTasksCompleted;

}
