package com.hostelfoodreview.menuservice.dto;

import com.hostelfoodreview.menuservice.entity.MealType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MenuRequest {
    private LocalDate date;
    private MealType mealType;
    private String items;
    private String createdBy;
}