package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AirlineMapper.class, AirportMapper.class })
public interface FlightMapper {
    Flight flightDtoToFlight(FlightRequestDto flightRequestDto);

    FlightResponseDto flightToFlightResponseDto(Flight flight);
}