package com.example.vkproject.exceptions;


public class CategoryException extends RuntimeException {
    public CategoryException(String invalid_category) {
        super(invalid_category);
    }
}

