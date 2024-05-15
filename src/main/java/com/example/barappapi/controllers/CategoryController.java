package com.example.barappapi.controllers;

import com.example.barappapi.dto.CreateCategoryDto;
import com.example.barappapi.models.Category;
import com.example.barappapi.models.Cocktail;
import com.example.barappapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        try {
            Category category = categoryService.createCategory(createCategoryDto);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cocktails")
    public ResponseEntity<Map<String, List<Cocktail>>> getAllCategoriesWithCocktails() {
        Map<String, List<Cocktail>> categoriesWithCocktails = categoryService.getAllCategoriesWithCocktails();

        Map<String, List<Cocktail>> filteredCategories = categoriesWithCocktails.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new ResponseEntity<>(filteredCategories, HttpStatus.OK);
    }
}
