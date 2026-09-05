package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AirlineDto(
        @NotNull(message = "Airline name must be provided")
        String name,
        @NotNull(message = "Airline airport must be provided")
        Long airportId,
        @Email
        @NotNull(message = "Airline email must be provided")
        String email,
        @NotNull(message = "Airline contact phone must be provided")
        String contactPhone) {
}
