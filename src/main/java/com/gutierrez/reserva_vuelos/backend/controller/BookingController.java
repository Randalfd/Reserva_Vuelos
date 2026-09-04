package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.dto.BookingDto;
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
    public List<BookingDto> findAllBookings(){
        return bookingService.findAllBookings();
    }

    @GetMapping("/api/booking/{id}")
    public BookingDto findBookingById(@PathVariable Long id) {
        return bookingService.findBookingsById(id);
    }

    @PostMapping("/api/booking")
    public BookingDto saveBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }

    @PutMapping("/api/booking/{id}")
    public BookingDto updateBooking(@RequestBody BookingDto bookingDto, @PathVariable Long id) {
        return bookingService.update(bookingDto,id);
    }

    @DeleteMapping("/api/booking/{id}")
    public void removeBooking(@PathVariable Long id) {
        bookingService.remove(id);
    }

    // Custom Endpoints
}
