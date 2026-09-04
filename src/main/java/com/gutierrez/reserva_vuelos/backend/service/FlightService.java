package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;

import java.util.List;

public interface FlightService {
    List<FlightDto> findAllFlights();
    FlightDto findFlightById(Long id);
    FlightDto saveFlight(FlightDto flightDto);
    FlightDto updateFlight(FlightDto flightDto, Long id);
    void removeFlight(Long id);
    List<FlightDto> findByOrderByDepartureDesc();
}
