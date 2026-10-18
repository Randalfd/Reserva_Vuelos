package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerCreateRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerUpdateRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

   PassengerResponseDto toDto(Passenger passenger);

   @Mapping(target = "user.id", ignore = true)
   @Mapping(target = "user.role", ignore = true)
   @Mapping(target = "user.password", ignore = true)
   Passenger passengerDtoToPassenger(PassengerCreateRequestDto dto);

   @Mapping(target = "user.id", ignore = true)
   @Mapping(target = "user.role", ignore = true)
   @Mapping(target = "user.password", ignore = true)
   void updateEntity(PassengerUpdateRequestDto dto, @MappingTarget Passenger passenger);
}
