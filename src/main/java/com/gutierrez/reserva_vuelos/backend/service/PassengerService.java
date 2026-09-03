package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerDto;

import java.util.List;

public interface PassengerService {
    List<PassengerDto> findAllPassenger();
    PassengerDto findPassengerById(Long id);
    PassengerDto savePassenger(PassengerDto passengerDto);
    PassengerDto updatePassenger(PassengerDto passengerDto, Long id);
    void removePassenger(Long id);
}
