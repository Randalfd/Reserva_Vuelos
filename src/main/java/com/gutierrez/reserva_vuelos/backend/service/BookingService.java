package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;

import java.util.List;

public interface BookingService {
   List<Booking> findAllBookings();
   Booking findBookingsById(Long id);
   Booking save(Booking booking);
   Booking update(Booking booking, Long id);
   void remove(Long id);
}
