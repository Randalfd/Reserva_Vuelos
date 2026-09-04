package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.BookingDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-03T22:40:05-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDto bookingToBookingDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        long passengerId = 0L;
        long flightId = 0L;
        BookingStatus status = null;
        SeatType seatType = null;

        Long id = bookingPassengerId( booking );
        if ( id != null ) {
            passengerId = id;
        }
        Long id1 = bookingFlightId( booking );
        if ( id1 != null ) {
            flightId = id1;
        }
        status = booking.getStatus();
        seatType = booking.getSeatType();

        BookingDto bookingDto = new BookingDto( passengerId, flightId, status, seatType );

        return bookingDto;
    }

    @Override
    public Booking bookingDtoToBooking(BookingDto bookingDto) {
        if ( bookingDto == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.status( bookingDto.status() );
        booking.seatType( bookingDto.seatType() );

        return booking.build();
    }

    private Long bookingPassengerId(Booking booking) {
        Passenger passenger = booking.getPassenger();
        if ( passenger == null ) {
            return null;
        }
        return passenger.getId();
    }

    private Long bookingFlightId(Booking booking) {
        Flight flight = booking.getFlight();
        if ( flight == null ) {
            return null;
        }
        return flight.getId();
    }
}
