package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;

import java.util.List;

public interface FlightService {
    List<FlightResponseDto> findAllFlights();
    FlightResponseDto findFlightById(Long id) throws ResourceNotFoundException;
    FlightResponseDto saveFlight(FlightRequestDto flightRequestDto) throws ResourceNotFoundException;
    FlightResponseDto updateFlight(FlightRequestDto flightRequestDto, Long id) throws ResourceNotFoundException;
    void removeFlight(Long id) throws ResourceNotFoundException;

    List<FlightResponseDto> findByOrderByDepartureDesc();
}
