package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
   List<BookingResponseDto> findAllBookings();
   BookingResponseDto findBookingsById(Long id) throws ResourceNotFoundException;
   BookingResponseDto save(BookingRequestDto bookingRequestDto) throws ResourceNotFoundException;
   BookingResponseDto update(BookingRequestDto bookingRequestDto, Long id) throws ResourceNotFoundException;
   void remove(Long id) throws ResourceNotFoundException;

   List<BookingFlightDto> findBookingsWithFlight();
}
