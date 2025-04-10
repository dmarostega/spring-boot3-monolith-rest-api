package com.example.spring_boot3.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record OrderRecord(@NotBlank String client_id,  @NotEmpty List<OrderItemRecordDto> orderItems, Integer number) {
}
