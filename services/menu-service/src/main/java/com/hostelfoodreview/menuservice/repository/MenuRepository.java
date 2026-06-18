package com.hostelfoodreview.menuservice.repository;

import com.hostelfoodreview.menuservice.entity.Menu;
import com.hostelfoodreview.menuservice.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByDate(LocalDate date);

    Optional<Menu> findByDateAndMealType(LocalDate date, MealType mealType);
}