package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import com.gutierrez.reserva_vuelos.backend.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    // CRUD Endpoints
    @GetMapping("/api/flight")
    public List<FlightResponseDto> getAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/api/flight/{id}")
    public FlightResponseDto getFlightById(@PathVariable Long id) throws ResourceNotFoundException {
       return flightService.findFlightById(id);
    }

    @PostMapping("/api/flight")
    public FlightResponseDto saveFlight(@Valid @RequestBody FlightRequestDto flightRequestDto) throws ResourceNotFoundException {
        return flightService.saveFlight(flightRequestDto);
    }

    @PutMapping("/api/fligth/{id}")
    public FlightResponseDto updateFlight(@Valid @RequestBody FlightRequestDto flightRequestDto, @PathVariable Long id) throws ResourceNotFoundException {
        return flightService.updateFlight(flightRequestDto, id);
    }

    @DeleteMapping("/api/flight/{id}")
    public void removeFlight(@PathVariable Long id) throws ResourceNotFoundException {
        flightService.removeFlight(id);
    }

    // Custom Endpoints
    @GetMapping("/api/flight/sort")
    public List<FlightResponseDto> findByOrderByDepartureDesc() {
       return flightService.findByOrderByDepartureDesc();
    }
}
