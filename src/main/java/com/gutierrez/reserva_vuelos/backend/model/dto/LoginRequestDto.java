package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(@NotBlank String email, @NotBlank String password) {}
