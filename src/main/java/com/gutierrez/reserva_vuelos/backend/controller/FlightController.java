package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/api/flight")
    public List<FlightDto> getAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/api/flight/{id}")
    public FlightDto getFlightById(@PathVariable Long id) throws ResourceNotFoundException {
       return flightService.findFlightById(id);
    }

    @PostMapping("/api/flight")
    public FlightDto saveFlight(@Valid @RequestBody FlightDto flightDto) throws ResourceNotFoundException {
        return flightService.saveFlight(flightDto);
    }

    @PutMapping("/api/fligth/{id}")
    public FlightDto updateFlight(@Valid @RequestBody FlightDto flightDto, @PathVariable Long id) throws ResourceNotFoundException {
        return flightService.updateFlight(flightDto, id);
    }

    @DeleteMapping("/api/flight/{id}")
    public void removeFlight(@PathVariable Long id) throws ResourceNotFoundException {
        flightService.removeFlight(id);
    }

    @GetMapping("/api/flight/sort")
    public List<FlightDto> findByOrderByDepartureDesc() {
       return flightService.findByOrderByDepartureDesc();
    }
}