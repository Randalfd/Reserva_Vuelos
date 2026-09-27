package com.gutierrez.reserva_vuelos.backend.model.dto;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AirlineResponseDto(
        String name,
        Airport mainAirport,
        Set<Airport> airports,
        String email,
        String contactPhone) {
}
