package com.example.spring_boot3.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderItemRecordDto(@NotNull String product_id, @NotBlank String order_id, @Min(0) Integer quantity) {
}
