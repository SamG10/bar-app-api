package com.example.barappapi.dto;

import com.example.barappapi.models.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCocktailDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String image_url;

    @NotEmpty
    private String ingredients;

    @NotNull
    private Float price_L;

    @NotNull
    private Float price_M;

    @NotNull
    private Float price_S;

    private List<String> categories;
}
