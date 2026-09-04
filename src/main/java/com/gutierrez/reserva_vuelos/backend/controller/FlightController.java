package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerAdvice
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/api/flight")
    public List<FlightDto> getAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/api/flight/{id}")
    public FlightDto getFlightById(@PathVariable Long id) {
       return flightService.findFlightById(id);
    }

    @PostMapping("/api/flight")
    public FlightDto saveFlight(@RequestBody FlightDto flightDto) {
        return flightService.saveFlight(flightDto);
    }

    @PutMapping("/api/fligth/{id}")
    public FlightDto updateFlight(@RequestBody FlightDto flightDto, @PathVariable Long id) {
        return flightService.updateFlight(flightDto, id);
    }

    @DeleteMapping("/api/flight/{id}")
    public void removeFlight(@PathVariable Long id) {
        flightService.removeFlight(id);
    }

    @GetMapping("/api/flight/sort")
    public List<FlightDto> findByOrderByDepartureDesc() {
       return flightService.findByOrderByDepartureDesc();
    }
}