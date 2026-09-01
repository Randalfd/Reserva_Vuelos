package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import com.gutierrez.reserva_vuelos.backend.respository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findBookingsById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking, Long id) {
        Booking savedBooking = bookingRepository.findById(id).get();

        if(Objects.nonNull(booking.getPassenger())) {
            savedBooking.setPassenger(booking.getPassenger());
        }

        if(Objects.nonNull(booking.getFlight())) {
            savedBooking.setFlight(booking.getFlight());
        }

        if(Objects.nonNull(booking.getStatus()) && !"".equalsIgnoreCase(booking.getStatus())) {
            savedBooking.setStatus(booking.getStatus());
        }
        return bookingRepository.save(savedBooking);
    }

    @Override
    public void remove(Long id) {
        bookingRepository.deleteById(id);
    }
}
