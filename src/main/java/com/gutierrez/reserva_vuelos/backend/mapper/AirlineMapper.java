package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    AirlineMapper INSTANCE = Mappers.getMapper( AirlineMapper.class );

    @Mapping(source = "phone", target = "contact_phone" )
    AirlineDto airlineToAirlineDto(Airline airline);

    @Mapping(source = "contact_phone", target = "phone" )
    Airline airlineDtoToAirline(AirlineDto airlineDto);
}
