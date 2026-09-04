package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.BookingDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;

import java.util.List;

public interface BookingService {
   List<BookingDto> findAllBookings();
   BookingDto findBookingsById(Long id);
   BookingDto save(BookingDto bookingDto);
   BookingDto update(BookingDto bookingDto, Long id);
   void remove(Long id);
}
