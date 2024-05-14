package com.example.barappapi.dto;

import com.example.barappapi.enums.StatusType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderDto {
    @NotNull
    @NotEmpty
    private StatusType status;
}
