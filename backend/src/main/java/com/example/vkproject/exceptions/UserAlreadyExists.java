package com.example.vkproject.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(Long userId) {
        super(String.valueOf(userId));
    }
}
