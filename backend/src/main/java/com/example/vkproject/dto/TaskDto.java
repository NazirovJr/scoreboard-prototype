package com.example.vkproject.dto;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class TaskDto {
    @Id
    private Long id;

    private String name;

    private String description;

    private String category;
}
