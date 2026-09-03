package com.gutierrez.reserva_vuelos.backend.model.dto;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;

public record AirlineDto(String name,
                         Airport airport,
                         String email,
                         String contact_phone) {
}
