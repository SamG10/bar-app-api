package com.example.barappapi.repositories;

import com.example.barappapi.models.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, String> {
}
