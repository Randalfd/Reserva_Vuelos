package com.gutierrez.reserva_vuelos.backend.model.dto;

import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;

import java.time.LocalDate;

public record BookingFlightDto(Long bookingId,
                               BookingStatus status,
                               SeatType seatType,
                               Long flightId,
                               double price,
                               LocalDate departure,
                               LocalDate arrival,
                               String originAirport,
                               String destinationAirport,
                               String airline) {
}
