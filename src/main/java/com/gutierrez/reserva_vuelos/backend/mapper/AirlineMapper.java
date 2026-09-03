package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AirportMapper.class)
public interface AirlineMapper {
    @Mapping(source = "phone", target = "contactPhone" )
    @Mapping(source = "airport.id", target = "airportId")
    AirlineDto airlineToAirlineDto(Airline airline);

    @Mapping(source = "contactPhone", target = "phone" )
    Airline airlineDtoToAirline(AirlineDto airlineDto);
}
