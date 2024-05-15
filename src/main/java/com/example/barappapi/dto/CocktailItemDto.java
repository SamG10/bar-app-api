package com.example.barappapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CocktailItemDto {
    @NotNull
    private String id;

    @NotNull
    private Float price;
}