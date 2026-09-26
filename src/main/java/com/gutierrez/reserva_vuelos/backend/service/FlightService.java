package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;

import java.util.List;

public interface FlightService {
    List<FlightRequestDto> findAllFlights();
    FlightRequestDto findFlightById(Long id) throws ResourceNotFoundException;
    FlightRequestDto saveFlight(FlightRequestDto flightRequestDto) throws ResourceNotFoundException;
    FlightRequestDto updateFlight(FlightRequestDto flightRequestDto, Long id) throws ResourceNotFoundException;
    void removeFlight(Long id) throws ResourceNotFoundException;
    List<FlightRequestDto> findByOrderByDepartureDesc();
}
