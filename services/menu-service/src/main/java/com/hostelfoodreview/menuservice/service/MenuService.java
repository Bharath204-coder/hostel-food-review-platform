package com.hostelfoodreview.menuservice.service;

import com.hostelfoodreview.menuservice.entity.Menu;
import com.hostelfoodreview.menuservice.entity.MealType;
import com.hostelfoodreview.menuservice.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Admin creates a new menu
    public Menu createMenu(LocalDate date, MealType mealType, String items, String createdBy) {
        Menu menu = new Menu();
        menu.setDate(date);
        menu.setMealType(mealType);
        menu.setItems(items);
        menu.setCreatedBy(createdBy);
        menu.setCreatedAt(LocalDateTime.now());
        return menuRepository.save(menu);
    }

    // Student views all meals for a date
    public List<Menu> getMenusByDate(LocalDate date) {
        return menuRepository.findByDate(date);
    }

    // Get specific meal on a date
    public Menu getMenuByDateAndMealType(LocalDate date, MealType mealType) {
        return menuRepository.findByDateAndMealType(date, mealType)
                .orElseThrow(() -> new RuntimeException("Menu not found for " + date + " - " + mealType));
    }

    // Admin updates menu items
    public Menu updateMenu(Long id, String newItems) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.setItems(newItems);
        return menuRepository.save(menu);
    }

    // Admin deletes a menu
    public void deleteMenu(Long id) {
        menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menuRepository.deleteById(id);
    }
}