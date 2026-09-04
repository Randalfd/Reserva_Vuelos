package com.gutierrez.reserva_vuelos.backend.model.dto;

import java.time.LocalDate;

public record FlightDto(long originAirportId,
                        long destinationAirportId,
                        long airlineId,
                        double price,
                        LocalDate arrival,
                        LocalDate departure) {
}
