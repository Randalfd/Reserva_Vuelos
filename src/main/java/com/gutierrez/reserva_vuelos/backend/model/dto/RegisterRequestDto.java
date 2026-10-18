package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        String firstname,
        String lastname,
        @Email String email,
        @Size(min=8) String password) {}
