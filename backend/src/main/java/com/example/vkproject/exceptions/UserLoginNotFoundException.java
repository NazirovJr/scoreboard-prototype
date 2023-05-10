package com.example.vkproject.exceptions;

public class UserLoginNotFoundException extends RuntimeException{
    public UserLoginNotFoundException(String message) {
        super(message);
    }
}
