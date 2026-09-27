package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineResponseDto;

import java.util.List;

public interface AirlineService {
    List<AirlineResponseDto> findAllAirlines();
    AirlineResponseDto findAirlineById(Long id) throws ResourceNotFoundException;
    AirlineResponseDto saveAirline(AirlineRequestDto airline) throws  ResourceNotFoundException;
    AirlineResponseDto updateAirline(AirlineRequestDto airline, Long id) throws ResourceNotFoundException;
    void removeAirline(Long id) throws ResourceNotFoundException;
    AirlineResponseDto findByName(String name) throws ResourceNotFoundException;

}
