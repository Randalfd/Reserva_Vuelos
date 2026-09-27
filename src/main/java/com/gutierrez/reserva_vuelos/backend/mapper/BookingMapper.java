package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.BookingRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.BookingResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PassengerMapper.class, FlightMapper.class})
public interface BookingMapper {

    Booking bookingDtoToBooking(BookingRequestDto bookingRequestDto);

    BookingResponseDto bookingToBookingResponseDto(Booking booking);
}
