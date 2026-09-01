package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    // CRUD Endopoints
    @GetMapping("/api/booking")
    public List<Booking> findAllBookings(){
        return bookingService.findAllBookings();
    }

    @GetMapping("/api/booking/{id}")
    public Booking findBookingById(@PathVariable Long id) {
        return bookingService.findBookingsById(id);
    }

    @PostMapping("/api/booking")
    public Booking saveBooking(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @PutMapping("/api/booking/{id}")
    public Booking updateBooking(@RequestBody Booking booking, @PathVariable Long id) {
        return bookingService.update(booking,id);
    }

    @DeleteMapping("/api/booking/{id}")
    public void removeBooking(@PathVariable Long id) {
        bookingService.remove(id);
    }

    // Custom Endpoints
}
