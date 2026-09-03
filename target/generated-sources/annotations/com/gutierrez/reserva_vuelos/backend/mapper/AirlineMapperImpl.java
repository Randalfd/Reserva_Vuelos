package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-03T14:11:23-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class AirlineMapperImpl implements AirlineMapper {

    @Override
    public AirlineDto airlineToAirlineDto(Airline airline) {
        if ( airline == null ) {
            return null;
        }

        String contact_phone = null;
        String name = null;
        AirportDto airport = null;
        String email = null;

        contact_phone = airline.getPhone();
        name = airline.getName();
        airport = airportToAirportDto( airline.getAirport() );
        email = airline.getEmail();

        AirlineDto airlineDto = new AirlineDto( name, airport, email, contact_phone );

        return airlineDto;
    }

    @Override
    public Airline airlineDtoToAirline(AirlineDto airlineDto) {
        if ( airlineDto == null ) {
            return null;
        }

        Airline.AirlineBuilder airline = Airline.builder();

        airline.phone( airlineDto.contact_phone() );
        airline.name( airlineDto.name() );
        airline.airport( airportDtoToAirport( airlineDto.airport() ) );
        airline.email( airlineDto.email() );

        return airline.build();
    }

    protected AirportDto airportToAirportDto(Airport airport) {
        if ( airport == null ) {
            return null;
        }

        String address = null;
        String city = null;

        address = airport.getAddress();
        city = airport.getCity();

        String airport_name = null;
        String iCAO = null;

        AirportDto airportDto = new AirportDto( airport_name, address, iCAO, city );

        return airportDto;
    }

    protected Airport airportDtoToAirport(AirportDto airportDto) {
        if ( airportDto == null ) {
            return null;
        }

        Airport.AirportBuilder airport = Airport.builder();

        airport.address( airportDto.address() );
        airport.city( airportDto.city() );

        return airport.build();
    }
}
