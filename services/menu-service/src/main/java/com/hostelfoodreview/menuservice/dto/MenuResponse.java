package com.hostelfoodreview.menuservice.dto;

import com.hostelfoodreview.menuservice.entity.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MenuResponse {
    private Long id;
    private LocalDate date;
    private MealType mealType;
    private String items;
    private String createdBy;
    private LocalDateTime createdAt;
}