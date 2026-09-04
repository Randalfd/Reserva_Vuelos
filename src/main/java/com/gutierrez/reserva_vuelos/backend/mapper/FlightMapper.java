package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AirlineMapper.class, AirportMapper.class })
public interface FlightMapper {
    @Mapping(source = "originAirport.id", target = "originAirportId")
    @Mapping(source = "destinationAirport.id", target = "destinationAirportId")
    @Mapping(source = "airline.id", target = "airlineId")
    FlightDto flightToFlightDto(Flight flight);

    Flight flightDtoToFlight(FlightDto flightDto);
}