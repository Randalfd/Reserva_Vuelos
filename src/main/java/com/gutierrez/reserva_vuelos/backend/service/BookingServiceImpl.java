package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.BookingMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.respository.BookingRepository;
import com.gutierrez.reserva_vuelos.backend.respository.FlightRepository;
import com.gutierrez.reserva_vuelos.backend.respository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public List<BookingDto> findAllBookings() {
        return bookingRepository.findAll().
                stream().
                map(bookingMapper::bookingToBookingDto).
                toList();
    }

    @Override
    public BookingDto findBookingsById(Long id) throws ResourceNotFoundException {
        return bookingRepository.findById(id).
                map(bookingMapper::bookingToBookingDto).
                orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));
    }

    @Override
    public BookingDto save(BookingDto bookingDto) throws ResourceNotFoundException {
        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);

        Flight flight = flightRepository.findById(bookingDto.flightId()).
                orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));
        booking.setFlight(flight);

        Passenger passenger = passengerRepository.findById(bookingDto.passengerId()).
                orElseThrow(() -> new ResourceNotFoundException("Passenger Not Found"));
        booking.setPassenger(passenger);

        return bookingMapper.bookingToBookingDto(bookingRepository.save(booking));
    }

    @Override
    public BookingDto update(BookingDto bookingDto, Long id) throws ResourceNotFoundException {
        Booking savedBooking = bookingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));

        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);

        Flight flight = flightRepository.findById(bookingDto.flightId()).
                orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));
        booking.setFlight(flight);

        Passenger passenger = passengerRepository.findById(bookingDto.passengerId()).
                orElseThrow(() -> new ResourceNotFoundException("Passenger Not Found"));
        booking.setPassenger(passenger);

        if(Objects.nonNull(booking.getPassenger())) {
            savedBooking.setPassenger(booking.getPassenger());
        }

        if(Objects.nonNull(booking.getFlight())) {
            savedBooking.setFlight(booking.getFlight());
        }

        if(Objects.nonNull(booking.getStatus())) {
            savedBooking.setStatus(booking.getStatus());
        }

        if(Objects.nonNull(booking.getSeatType())) {
            savedBooking.setSeatType(booking.getSeatType());
        }

        return bookingMapper.bookingToBookingDto(bookingRepository.save(savedBooking));
    }

    @Override
    public void remove(Long id) throws ResourceNotFoundException {
        bookingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));

        bookingRepository.deleteById(id);
    }
}
