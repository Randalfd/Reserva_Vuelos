package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<FlightResponseDto> findAllFlights();
    FlightResponseDto findFlightById(Long id) throws ResourceNotFoundException;
    FlightResponseDto saveFlight(FlightRequestDto flightRequestDto) throws ResourceNotFoundException;
    FlightResponseDto updateFlight(FlightRequestDto flightRequestDto, Long id) throws ResourceNotFoundException;
    void removeFlight(Long id) throws ResourceNotFoundException;

    List<FlightResponseDto> findByOrderByDepartureDesc();
    Page<FlightResponseDto> findByDepartureBetween(LocalDate start, LocalDate end, int pageIndex, int pageSize);
    Page<FlightResponseDto> findByArrivalAfter(LocalDate arrival, int pageIndex, int pageSize);
}
