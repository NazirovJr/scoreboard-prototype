package com.example.vkproject.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDto {
    @Id
    private Long id;

    private String username;

    private String email;

    private String password;
}
