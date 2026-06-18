package com.hostelfoodreview.menuservice.controller;

import com.hostelfoodreview.menuservice.dto.MenuRequest;
import com.hostelfoodreview.menuservice.entity.Menu;
import com.hostelfoodreview.menuservice.entity.MealType;
import com.hostelfoodreview.menuservice.service.MenuService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Admin creates a menu
    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody MenuRequest request) {
        Menu menu = menuService.createMenu(
                request.getDate(),
                request.getMealType(),
                request.getItems(),
                request.getCreatedBy()
        );
        return ResponseEntity.ok(menu);
    }

    // Student views all meals for a date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Menu>> getMenusByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(menuService.getMenusByDate(date));
    }

    // Get specific meal on a date
    @GetMapping("/date/{date}/meal/{mealType}")
    public ResponseEntity<Menu> getMenuByDateAndMealType(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable MealType mealType) {
        return ResponseEntity.ok(menuService.getMenuByDateAndMealType(date, mealType));
    }

    // Admin updates menu items
    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(
            @PathVariable Long id,
            @RequestBody MenuRequest request) {
        return ResponseEntity.ok(menuService.updateMenu(id, request.getItems()));
    }

    // Admin deletes a menu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}