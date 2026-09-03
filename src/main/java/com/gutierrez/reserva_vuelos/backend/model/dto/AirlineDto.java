package com.gutierrez.reserva_vuelos.backend.model.dto;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;

public record AirlineDto(String name,
                         AirportDto airport,
                         String email,
                         String contact_phone) {
}
