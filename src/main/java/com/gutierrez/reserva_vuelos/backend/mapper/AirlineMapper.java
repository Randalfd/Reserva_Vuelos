package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AirportMapper.class)
public interface AirlineMapper {
    @Mapping(source = "contactPhone", target = "phone" )
    Airline airlineRequestDtoToAirline(AirlineRequestDto airlineRequestDto);

    @Mapping(source = "phone", target = "contactPhone" )
    AirlineResponseDto airlineToAirlineResponseDto(Airline airline);
}
