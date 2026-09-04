package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerDto;

import java.util.List;

public interface PassengerService {
    List<PassengerDto> findAllPassenger();
    PassengerDto findPassengerById(Long id) throws ResourceNotFoundException;
    PassengerDto savePassenger(PassengerDto passengerDto);
    PassengerDto updatePassenger(PassengerDto passengerDto, Long id) throws ResourceNotFoundException;
    void removePassenger(Long id) throws ResourceNotFoundException;
}
