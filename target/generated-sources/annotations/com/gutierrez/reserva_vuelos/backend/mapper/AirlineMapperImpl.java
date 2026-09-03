package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-03T15:43:17-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class AirlineMapperImpl implements AirlineMapper {

    @Override
    public AirlineDto airlineToAirlineDto(Airline airline) {
        if ( airline == null ) {
            return null;
        }

        String contactPhone = null;
        Long airportId = null;
        String name = null;
        String email = null;

        contactPhone = airline.getPhone();
        airportId = airlineAirportId( airline );
        name = airline.getName();
        email = airline.getEmail();

        AirlineDto airlineDto = new AirlineDto( name, airportId, email, contactPhone );

        return airlineDto;
    }

    @Override
    public Airline airlineDtoToAirline(AirlineDto airlineDto) {
        if ( airlineDto == null ) {
            return null;
        }

        Airline.AirlineBuilder airline = Airline.builder();

        airline.phone( airlineDto.contactPhone() );
        airline.name( airlineDto.name() );
        airline.email( airlineDto.email() );

        return airline.build();
    }

    private Long airlineAirportId(Airline airline) {
        Airport airport = airline.getAirport();
        if ( airport == null ) {
            return null;
        }
        return airport.getId();
    }
}
