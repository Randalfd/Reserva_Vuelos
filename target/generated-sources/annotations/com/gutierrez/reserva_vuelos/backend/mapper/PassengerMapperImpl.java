package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-04T18:35:46-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class PassengerMapperImpl implements PassengerMapper {

    @Override
    public PassengerDto passengerTopassengerDto(Passenger passenger) {
        if ( passenger == null ) {
            return null;
        }

        String firstname = null;
        String lastname = null;
        String email = null;

        firstname = passenger.getFirstname();
        lastname = passenger.getLastname();
        email = passenger.getEmail();

        PassengerDto passengerDto = new PassengerDto( firstname, lastname, email );

        return passengerDto;
    }

    @Override
    public Passenger passengerDtoToPassenger(PassengerDto passengerDto) {
        if ( passengerDto == null ) {
            return null;
        }

        Passenger.PassengerBuilder passenger = Passenger.builder();

        passenger.firstname( passengerDto.firstname() );
        passenger.lastname( passengerDto.lastname() );
        passenger.email( passengerDto.email() );

        return passenger.build();
    }
}
