package com.gutierrez.reserva_vuelos.backend.model.dto;

public record AirportDto(String airport_name,
                         String address,
                         String ICAO,
                         String city) {
}
