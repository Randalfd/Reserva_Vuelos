package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;

import java.util.List;

public interface BookingService {
   List<Booking> findAllBookings();
}
