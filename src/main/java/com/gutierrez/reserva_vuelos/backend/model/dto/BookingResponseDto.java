package com.gutierrez.reserva_vuelos.backend.model.dto;


import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;
import jakarta.validation.constraints.NotNull;

public record BookingResponseDto(
        @NotNull(message = "Booking passenger must be provided")
        PassengerDto passenger,

        @NotNull(message = "Booking flight must be provided")
        FlightResponseDto flightId,

        @NotNull(message = "Booking status must be provided")
        BookingStatus status,

        @NotNull(message = "Booking seatType must be provided")
        SeatType seatType) {
}
