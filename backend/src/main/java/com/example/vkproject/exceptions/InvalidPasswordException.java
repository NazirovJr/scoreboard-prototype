package com.example.vkproject.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String invalid_password) {
        super(invalid_password);
    }
}
