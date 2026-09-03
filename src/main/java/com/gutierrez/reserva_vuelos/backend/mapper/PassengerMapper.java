package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

   PassengerDto passengerTopassengerDto(Passenger passenger);

   Passenger passengerDtoToPassenger(PassengerDto passengerDto);
}
