package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AirportMapper.class)
public interface AirlineMapper {
    @Mapping(source = "phone", target = "contactPhone" )
    @Mapping(source = "mainAirport.id", target = "airportId")
    AirlineRequestDto airlineToAirlineRequestDto(Airline airline);

    @Mapping(source = "contactPhone", target = "phone" )
    Airline airlineRequestDtoToAirline(AirlineRequestDto airlineRequestDto);

    AirlineResponseDto airlineToArlineResponseDto(Airline airline);
}
