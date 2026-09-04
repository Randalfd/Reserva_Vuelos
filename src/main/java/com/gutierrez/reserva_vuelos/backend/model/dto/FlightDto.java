package com.gutierrez.reserva_vuelos.backend.model.dto;

import java.time.LocalDate;

public record FlightDto(Long originAirportId,
                        Long destinationAirportId,
                        Long airlineId,
                        double price,
                        LocalDate arrival,
                        LocalDate departure) {
}
