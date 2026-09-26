package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AirlineRequestDto(
        @NotNull(message = "Airline name must be provided")
        String name,

        @NotNull(message = "Airline airport must be provided")
        Long mainAirportId,

        Set<Long> airportsId,

        @Email
        @NotNull(message = "Airline email must be provided")
        String email,

        @NotNull(message = "Airline contact phone must be provided")
        String contactPhone) {
}
