package com.example.vkproject.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserCategoryRatingDto {
    private ArrayList<CategoryRatingDto> categoryRatings = new ArrayList<>();
}
