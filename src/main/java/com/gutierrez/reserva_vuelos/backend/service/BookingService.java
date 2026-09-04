package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;

import java.util.List;

public interface BookingService {
   List<BookingDto> findAllBookings();
   BookingDto findBookingsById(Long id) throws ResourceNotFoundException;
   BookingDto save(BookingDto bookingDto) throws ResourceNotFoundException;
   BookingDto update(BookingDto bookingDto, Long id) throws ResourceNotFoundException;
   void remove(Long id) throws ResourceNotFoundException;
   List<BookingFlightDto> findBookingsWithFlight();
}
