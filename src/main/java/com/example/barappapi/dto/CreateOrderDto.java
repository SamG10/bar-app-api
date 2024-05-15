package com.example.barappapi.dto;

import com.example.barappapi.enums.StatusType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    @NotNull
    private Integer number;

    @NotNull
    private Float total_price;

    @NotNull
    private StatusType status;

    private List<CocktailItemDto> cocktails;
}
