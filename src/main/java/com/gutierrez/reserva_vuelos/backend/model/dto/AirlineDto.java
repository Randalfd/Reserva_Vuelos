package com.gutierrez.reserva_vuelos.backend.model.dto;

public record AirlineDto(String name,
                         Long airportId,
                         String email,
                         String contactPhone) {
}
