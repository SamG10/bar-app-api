package com.example.barappapi.services;

import com.example.barappapi.dto.CreateCocktailDto;
import com.example.barappapi.models.Category;
import com.example.barappapi.models.CategoryCocktail;
import com.example.barappapi.models.Cocktail;
import com.example.barappapi.repositories.CategoryCocktailRepository;
import com.example.barappapi.repositories.CategoryRepository;
import com.example.barappapi.repositories.CocktailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CocktailService {
    @Autowired
    private CocktailRepository cocktailRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryCocktailRepository categoryCocktailRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Cocktail createCocktail(CreateCocktailDto createCocktailDto) {
        Cocktail cocktail = modelMapper.map(createCocktailDto, Cocktail.class);

        cocktail = cocktailRepository.save(cocktail);

        for(String categoryId : createCocktailDto.getCategories()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));

            CategoryCocktail categoryCocktail = new CategoryCocktail();
            categoryCocktail.setCocktail(cocktail);
            categoryCocktail.setCategory(category);

            categoryCocktailRepository.save(categoryCocktail);
        }

        return cocktail;
    }

    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }
}
