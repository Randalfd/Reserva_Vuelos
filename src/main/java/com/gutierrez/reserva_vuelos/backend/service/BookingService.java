package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;

import java.util.List;

public interface BookingService {
   List<BookingRequestDto> findAllBookings();
   BookingRequestDto findBookingsById(Long id) throws ResourceNotFoundException;
   BookingRequestDto save(BookingRequestDto bookingRequestDto) throws ResourceNotFoundException;
   BookingRequestDto update(BookingRequestDto bookingRequestDto, Long id) throws ResourceNotFoundException;
   void remove(Long id) throws ResourceNotFoundException;
   List<BookingFlightDto> findBookingsWithFlight();
}
