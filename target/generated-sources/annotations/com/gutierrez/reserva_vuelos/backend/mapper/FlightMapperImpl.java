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
    date = "2026-09-05T13:15:40-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4.1 (Red Hat, Inc.)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDto flightToFlightDto(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        Long originAirportId = null;
        Long destinationAirportId = null;
        Long airlineId = null;
        double price = 0.0d;
        LocalDate arrival = null;
        LocalDate departure = null;

        originAirportId = flightOriginAirportId( flight );
        destinationAirportId = flightDestinationAirportId( flight );
        airlineId = flightAirlineId( flight );
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
