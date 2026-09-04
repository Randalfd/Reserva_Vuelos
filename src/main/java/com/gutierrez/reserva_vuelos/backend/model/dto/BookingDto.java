package com.gutierrez.reserva_vuelos.backend.model.dto;


import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;

public record BookingDto(Long passengerId,
                         Long flightId,
                         BookingStatus status,
                         SeatType seatType) {
}
