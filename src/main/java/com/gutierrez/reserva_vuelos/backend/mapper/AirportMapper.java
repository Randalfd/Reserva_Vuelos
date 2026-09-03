package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(source = "icao", target = "ICAO" )
    @Mapping(source = "name", target = "airport_name")
    AirportDto airportToAriportDTO(Airport airport);

    @Mapping(source = "ICAO", target = "icao" )
    @Mapping(source = "airport_name", target = "name")
    Airport airportDtoToAirport(AirportDto airportDto);
}
