package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record FlightDto(
        @NotNull(message = "Flight Origin Airport must be provided")
        Long originAirportId,
        @NotNull(message = "Flight destination Airport must be provided")
        Long destinationAirportId,
        @NotNull(message = "Flight airline must be provided")
        Long airlineId,
        @PositiveOrZero(message = "price must be greater or equals 0")
        double price,
        @NotNull(message = "Flight arrival date must be provided")
        @FutureOrPresent(message = "Arrival cannot be in the past")
        LocalDate arrival,
        @NotNull(message = "Flight departure must be provided")
        @FutureOrPresent(message = "Departure cannot be in the past")
        LocalDate departure) {
}
