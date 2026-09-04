package com.gutierrez.reserva_vuelos.backend.respository;

import com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT new com.gutierrez.reserva_vuelos.backend.model.dto.BookingFlightDto(" +
            "b.id, b.status, b.seatType, f.id, f.price, f.departure, f.arrival, " +
            "f.originAirport.name, f.destinationAirport.name, f.airline.name) " +
            "FROM Booking b JOIN b.flight f " +
            "JOIN f.originAirport JOIN f.destinationAirport JOIN f.airline")
    List<BookingFlightDto> findBookingsWithFlight();
}
