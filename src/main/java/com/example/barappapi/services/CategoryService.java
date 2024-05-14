package com.example.barappapi.services;

import com.example.barappapi.dto.CreateCategoryDto;
import com.example.barappapi.models.Barmaker;
import com.example.barappapi.models.Category;
import com.example.barappapi.models.Cocktail;
import com.example.barappapi.repositories.CategoryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BarmakerService barmakerService;

    public Category createCategory(CreateCategoryDto createCategoryDto) {
        Barmaker barmaker = barmakerService.getCurrentBarmaker();
        Category category = new Category();
        category.setName(createCategoryDto.getName());
        category.setBarmaker(barmaker);
        return categoryRepository.save(category);
    }

    public Map<String, List<Cocktail>> getAllCategoriesWithCocktails() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .collect(Collectors.toMap(
                        Category::getName, // Clé : nom de la catégorie
                        Category::getCocktails
                ));
    }

}
