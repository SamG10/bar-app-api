package com.example.barappapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotEmpty
    private String name;
}
