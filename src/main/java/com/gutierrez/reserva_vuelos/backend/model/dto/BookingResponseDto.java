package com.gutierrez.reserva_vuelos.backend.model.dto;


import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;
import jakarta.validation.constraints.NotNull;

public record BookingResponseDto(
        PassengerDto passenger,
        FlightResponseDto flight,
        BookingStatus status,
        SeatType seatType) {
}
