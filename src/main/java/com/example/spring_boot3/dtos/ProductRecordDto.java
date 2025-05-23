package com.example.spring_boot3.dtos;

import java.math.BigDecimal;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotBlank String name,@NotNull BigDecimal value) 
{
}
