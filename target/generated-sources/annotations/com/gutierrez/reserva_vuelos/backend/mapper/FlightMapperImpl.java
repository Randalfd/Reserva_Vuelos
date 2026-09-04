package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-03T21:37:29-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDto flightToFlightDto(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        long originAirportId = 0L;
        long destinationAirportId = 0L;
        long airlineId = 0L;
        double price = 0.0d;
        LocalDate arrival = null;
        LocalDate departure = null;

        Long id = flightOriginAirportId( flight );
        if ( id != null ) {
            originAirportId = id;
        }
        Long id1 = flightDestinationAirportId( flight );
        if ( id1 != null ) {
            destinationAirportId = id1;
        }
        Long id2 = flightAirlineId( flight );
        if ( id2 != null ) {
            airlineId = id2;
        }
        price = flight.getPrice();
        arrival = flight.getArrival();
        departure = flight.getDeparture();

        FlightDto flightDto = new FlightDto( originAirportId, destinationAirportId, airlineId, price, arrival, departure );

        return flightDto;
    }

    @Override
    public Flight flightDtoToFlight(FlightDto flightDto) {
        if ( flightDto == null ) {
            return null;
        }

        Flight.FlightBuilder flight = Flight.builder();

        flight.price( flightDto.price() );
        flight.departure( flightDto.departure() );
        flight.arrival( flightDto.arrival() );

        return flight.build();
    }

    private Long flightOriginAirportId(Flight flight) {
        Airport originAirport = flight.getOriginAirport();
        if ( originAirport == null ) {
            return null;
        }
        return originAirport.getId();
    }

    private Long flightDestinationAirportId(Flight flight) {
        Airport destinationAirport = flight.getDestinationAirport();
        if ( destinationAirport == null ) {
            return null;
        }
        return destinationAirport.getId();
    }

    private Long flightAirlineId(Flight flight) {
        Airline airline = flight.getAirline();
        if ( airline == null ) {
            return null;
        }
        return airline.getId();
    }
}
