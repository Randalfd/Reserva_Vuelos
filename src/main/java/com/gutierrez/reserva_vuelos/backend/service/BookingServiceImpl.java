package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.BookingMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.respository.BookingRepository;
import com.gutierrez.reserva_vuelos.backend.respository.FlightRepository;
import com.gutierrez.reserva_vuelos.backend.respository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
  @Autowired
  BookingRepository bookingRepository;

  @Autowired
  BookingMapper bookingMapper;
  @Autowired
  FlightRepository flightRepository;

  @Autowired
  PassengerRepository passengerRepository;


  @Override
  public List<BookingResponseDto> findAllBookings() {
    return bookingRepository.findAll().stream().map(bookingMapper::bookingToBookingResponseDto).toList();
  }

  @Override
  public BookingResponseDto findBookingsById(Long id) throws ResourceNotFoundException {
    return bookingRepository.findById(id).map(bookingMapper::bookingToBookingResponseDto).orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));
  }

  @Override
  public BookingResponseDto save(BookingRequestDto bookingRequestDto) throws ResourceNotFoundException {
    Booking booking = bookingMapper.bookingDtoToBooking(bookingRequestDto);

    Flight flight = flightRepository.findById(bookingRequestDto.flightId()).orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));
    booking.setFlight(flight);

    Passenger passenger = passengerRepository.findById(bookingRequestDto.passengerId()).orElseThrow(() -> new ResourceNotFoundException("Passenger Not Found"));
    booking.setPassenger(passenger);

    return bookingMapper.bookingToBookingResponseDto(bookingRepository.save(booking));
  }

  @Override
  public BookingResponseDto update(BookingRequestDto bookingRequestDto, Long id) throws ResourceNotFoundException {
    Booking savedBooking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));

    Booking booking = bookingMapper.bookingDtoToBooking(bookingRequestDto);

    Flight flight = flightRepository.findById(bookingRequestDto.flightId()).orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));
    booking.setFlight(flight);

    Passenger passenger = passengerRepository.findById(bookingRequestDto.passengerId()).orElseThrow(() -> new ResourceNotFoundException("Passenger Not Found"));
    booking.setPassenger(passenger);

    savedBooking.setPassenger(booking.getPassenger());

    savedBooking.setFlight(booking.getFlight());

    savedBooking.setStatus(booking.getStatus());

    savedBooking.setSeatType(booking.getSeatType());

    return bookingMapper.bookingToBookingResponseDto(bookingRepository.save(savedBooking));
  }

  @Override
  public void remove(Long id) throws ResourceNotFoundException {
    bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));

    bookingRepository.deleteById(id);
  }

  @Override
  public List<BookingFlightDto> findBookingsWithFlight() {
    return bookingRepository.findBookingsWithFlight();
  }

  @Override
  public List<Long> countPassengerBookings() {
    return bookingRepository.countPassengerBooking();
  }

  @Override
  public List<BookingResponseDto> findCancelledBookings() {
    return bookingRepository.findCancelledBookings().
            stream().
            map(bookingMapper::bookingToBookingResponseDto).
            toList();
  }
}
