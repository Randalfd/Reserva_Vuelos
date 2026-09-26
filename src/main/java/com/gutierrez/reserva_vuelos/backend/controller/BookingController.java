package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;
import com.gutierrez.reserva_vuelos.backend.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    // CRUD Endopoints
    @GetMapping("/api/booking")
    public List<BookingRequestDto> findAllBookings(){
        return bookingService.findAllBookings();
    }

    @GetMapping("/api/booking/{id}")
    public BookingRequestDto findBookingById(@PathVariable Long id) throws ResourceNotFoundException {
        return bookingService.findBookingsById(id);
    }

    @PostMapping("/api/booking")
    public BookingRequestDto saveBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) throws ResourceNotFoundException {
        return bookingService.save(bookingRequestDto);
    }

    @PutMapping("/api/booking/{id}")
    public BookingRequestDto updateBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto, @PathVariable Long id) throws ResourceNotFoundException {
        return bookingService.update(bookingRequestDto,id);
    }

    @DeleteMapping("/api/booking/{id}")
    public void removeBooking(@PathVariable Long id) throws ResourceNotFoundException {
        bookingService.remove(id);
    }

    // Custom Endpoints
    @GetMapping("/api/booking/findBookingWithFlight")
    public List<BookingFlightDto> findBookingWithFlight() {
        return bookingService.findBookingsWithFlight();
    }

}
