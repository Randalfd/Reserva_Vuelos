package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;
import com.gutierrez.reserva_vuelos.backend.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

  @Autowired
  BookingService bookingService;

  // CRUD Endopoints
  @GetMapping("/api/booking")
  @PreAuthorize("isAuthenticated()")
  public List<BookingResponseDto> findAllBookings() {
    return bookingService.findAllBookings();
  }

  @GetMapping("/api/booking/{id}")
  @PreAuthorize("isAuthenticated()")
  public BookingResponseDto findBookingById(@PathVariable Long id) throws ResourceNotFoundException {
    return bookingService.findBookingsById(id);
  }

  @PostMapping("/api/booking")
  @PreAuthorize("hasRole('ADMIN')")
  public BookingResponseDto saveBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) throws ResourceNotFoundException {
    return bookingService.save(bookingRequestDto);
  }

  @PutMapping("/api/booking/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public BookingResponseDto updateBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto, @PathVariable Long id) throws ResourceNotFoundException {
    return bookingService.update(bookingRequestDto, id);
  }

  @DeleteMapping("/api/booking/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void removeBooking(@PathVariable Long id) throws ResourceNotFoundException {
    bookingService.remove(id);
  }

  // Custom Endpoints
  @GetMapping("/api/booking/findBookingWithFlight")
  @PreAuthorize("isAuthenticated()")
  public List<BookingFlightDto> findBookingWithFlight() {
    return bookingService.findBookingsWithFlight();
  }

  @GetMapping("/api/booking/countPassengerBookings")
  @PreAuthorize("isAuthenticated()")
  public List<Long> countPassengerBookings() {
    return bookingService.countPassengerBookings();
  }

  @GetMapping("/api/booking/findCancelledBookings")
  @PreAuthorize("isAuthenticated()")
  public List<BookingResponseDto> findCancelledBookings() {
    return bookingService.findCancelledBookings();
  }
}

