package com.gutierrez.reserva_vuelos.backend.model.dto;


import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;

public record BookingDto(long passengerId,
                         long flightId,
                         BookingStatus status,
                         SeatType seatType) {
}
