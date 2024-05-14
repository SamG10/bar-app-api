package com.example.barappapi.controllers;

import com.example.barappapi.dto.CreateCategoryDto;
import com.example.barappapi.dto.CreateCocktailDto;
import com.example.barappapi.dto.UpdateOrderDto;
import com.example.barappapi.models.Category;
import com.example.barappapi.models.Cocktail;
import com.example.barappapi.models.Order;
import com.example.barappapi.services.CategoryService;
import com.example.barappapi.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {
    @Autowired
    private CocktailService cocktailService;

    @PostMapping()
    public ResponseEntity<Cocktail> createCocktail(@RequestBody CreateCocktailDto createCocktailDto) {
        try {
            Cocktail cocktail = cocktailService.createCocktail(createCocktailDto);
            return new ResponseEntity<>(cocktail, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Cocktail>> getAllCocktails() {
        try {
            List<Cocktail> cocktails = cocktailService.getAllCocktails();
            return new ResponseEntity<>(cocktails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
