package com.gutierrez.reserva_vuelos.backend.controller;

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
    public List<Flight> getAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/api/flight/{id}")
    public Flight getFlightById(@PathVariable Long id) {
       return flightService.findFlightById(id);
    }

    @PostMapping("/api/flight")
    public Flight saveFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @PutMapping("/api/fligth/{id}")
    public Flight updateFlight(@RequestBody Flight flight, @PathVariable Long id) {
        return flightService.updateFlight(flight, id);
    }

    @DeleteMapping("/api/flight/{id}")
    public void removeFlight(@PathVariable Long id) {
        flightService.removeFlight(id);
    }

    @GetMapping("/api/flight/sort")
    public List<Flight> findByOrderByDepartureDesc() {
       return flightService.findByOrderByDepartureDesc();
    }
}