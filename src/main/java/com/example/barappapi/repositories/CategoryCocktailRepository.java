package com.example.barappapi.repositories;

import com.example.barappapi.models.CategoryCocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCocktailRepository extends JpaRepository<CategoryCocktail, String> {
}
