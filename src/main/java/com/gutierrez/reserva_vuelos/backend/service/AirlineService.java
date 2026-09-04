package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    List<AirlineDto> findAllAirlines();
    AirlineDto findAirlineById(Long id) throws ResourceNotFoundException;
    AirlineDto saveAirline(AirlineDto airline) throws  ResourceNotFoundException;
    AirlineDto updateAirline(AirlineDto airline, Long id) throws ResourceNotFoundException;
    void removeAirline(Long id) throws ResourceNotFoundException;
    AirlineDto findByName(String name) throws ResourceNotFoundException;

}
