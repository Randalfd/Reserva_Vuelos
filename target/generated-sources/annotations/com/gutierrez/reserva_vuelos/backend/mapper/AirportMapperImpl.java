package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-05T12:01:36-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class AirportMapperImpl implements AirportMapper {

    @Override
    public AirportDto airportToAriportDTO(Airport airport) {
        if ( airport == null ) {
            return null;
        }

        String iCAO = null;
        String airport_name = null;
        String address = null;
        String city = null;

        iCAO = airport.getIcao();
        airport_name = airport.getName();
        address = airport.getAddress();
        city = airport.getCity();

        AirportDto airportDto = new AirportDto( airport_name, address, iCAO, city );

        return airportDto;
    }

    @Override
    public Airport airportDtoToAirport(AirportDto airportDto) {
        if ( airportDto == null ) {
            return null;
        }

        Airport.AirportBuilder airport = Airport.builder();

        airport.icao( airportDto.ICAO() );
        airport.name( airportDto.airport_name() );
        airport.address( airportDto.address() );
        airport.city( airportDto.city() );

        return airport.build();
    }
}
