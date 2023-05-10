package com.example.vkproject.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class UserRatingDto {
    private Integer rating;
    private Long total;
}
