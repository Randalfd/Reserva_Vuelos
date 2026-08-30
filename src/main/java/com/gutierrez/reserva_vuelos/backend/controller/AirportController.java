package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/api/airport")
    public List<Airport> findAllAirports() {
        return airportService.findAllAirports();
    }

    @GetMapping("/api/airport/{id}")
    public Airport findAirportById(@PathVariable Long id) {
        return airportService.findAirportById(id);
    }

    @PostMapping("/api/airport")
    public Airport saveAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }

    @PutMapping("/api/airport/{id}")
    public Airport updateAirport(@RequestBody Airport airport, @PathVariable Long id) {
        return airportService.updateAirport(airport, id);
    }

    @DeleteMapping("/api/airport/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.remove(id);
    }
}
