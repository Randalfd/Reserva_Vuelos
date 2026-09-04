package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.BookingDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PassengerMapper.class, FlightMapper.class})
public interface BookingMapper {

    @Mapping(source = "passenger.id", target = "passengerId")
    @Mapping(source = "flight.id", target = "flightId")
    BookingDto bookingToBookingDto(Booking booking);

    Booking bookingDtoToBooking(BookingDto bookingDto);
}
