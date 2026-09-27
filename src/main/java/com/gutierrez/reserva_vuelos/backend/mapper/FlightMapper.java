package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AirlineMapper.class, AirportMapper.class })
public interface FlightMapper {
    Flight flightDtoToFlight(FlightRequestDto flightRequestDto);

    @Mapping(source = "originAirport", target = "originAirportId")
    @Mapping(source = "destinationAirport", target = "destinationAirportId")
    @Mapping(source = "airline", target = "airlineId")
    FlightResponseDto flightToFlightResponseDto(Flight flight);
}