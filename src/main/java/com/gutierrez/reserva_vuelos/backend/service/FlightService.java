package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;

import java.util.List;

public interface FlightService {
    List<FlightDto> findAllFlights();
    FlightDto findFlightById(Long id) throws ResourceNotFoundException;
    FlightDto saveFlight(FlightDto flightDto) throws ResourceNotFoundException;
    FlightDto updateFlight(FlightDto flightDto, Long id) throws ResourceNotFoundException;
    void removeFlight(Long id) throws ResourceNotFoundException;
    List<FlightDto> findByOrderByDepartureDesc();
}
