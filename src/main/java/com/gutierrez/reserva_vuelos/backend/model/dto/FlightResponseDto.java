package com.gutierrez.reserva_vuelos.backend.model.dto;

import java.time.LocalDate;

public record FlightResponseDto(
        AirportDto originAirport,
        AirportDto destinationAirport,
        AirlineResponseDto airline,
        double price,
        LocalDate arrival,
        LocalDate departure) {
}
