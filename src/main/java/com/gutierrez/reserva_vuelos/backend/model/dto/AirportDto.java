package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.NotNull;

public record AirportDto(
        @NotNull(message = "Airport Name must be provided")
        String airport_name,
        @NotNull(message = "Airport address must be provided")
        String address,
        @NotNull(message = "Airport ICAO must be provided")
        String ICAO,
        @NotNull(message = "Airport city must be provided")
        String city) {
}
