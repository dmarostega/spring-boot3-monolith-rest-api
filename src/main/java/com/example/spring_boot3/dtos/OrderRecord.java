package com.example.spring_boot3.dtos;

import jakarta.validation.constraints.NotBlank;

public record OrderRecord(@NotBlank String client_id, @NotBlank String order_item_id, Integer number) {
}
