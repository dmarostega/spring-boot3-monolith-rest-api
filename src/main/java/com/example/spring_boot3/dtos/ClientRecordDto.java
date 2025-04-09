package com.example.spring_boot3.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRecordDto(@NotBlank String name, @NotNull Integer docNumber, LocalDate birthday) 
{
}
