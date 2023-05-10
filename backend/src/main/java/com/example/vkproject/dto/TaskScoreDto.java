package com.example.vkproject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskScoreDto {
    private Long id;
    private String name;
    private double percentage;

    public TaskScoreDto(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }
}