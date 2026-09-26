package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;

import java.util.List;

public interface AirlineService {
    List<AirlineRequestDto> findAllAirlines();
    AirlineRequestDto findAirlineById(Long id) throws ResourceNotFoundException;
    AirlineRequestDto saveAirline(AirlineRequestDto airline) throws  ResourceNotFoundException;
    AirlineRequestDto updateAirline(AirlineRequestDto airline, Long id) throws ResourceNotFoundException;
    void removeAirline(Long id) throws ResourceNotFoundException;
    AirlineRequestDto findByName(String name) throws ResourceNotFoundException;

}
