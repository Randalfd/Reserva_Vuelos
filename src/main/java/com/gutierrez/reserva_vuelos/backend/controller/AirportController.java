package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/api/airport")
    public List<AirportDto> findAllAirports() {
        return airportService.findAllAirports();
    }

    @GetMapping("/api/airport/{id}")
    public AirportDto findAirportById(@PathVariable Long id) throws ResourceNotFoundException {
        return airportService.findAirportById(id);
    }

    @PostMapping("/api/airport")
    public AirportDto saveAirport(@Valid @RequestBody AirportDto airportDto) {
        return airportService.saveAirport(airportDto);
    }

    @PutMapping("/api/airport/{id}")
    public AirportDto updateAirport(@Valid @RequestBody AirportDto airport, @PathVariable Long id) throws ResourceNotFoundException {
        return airportService.updateAirport(airport, id);
    }

    @DeleteMapping("/api/airport/{id}")
    public void deleteAirport(@PathVariable Long id) throws ResourceNotFoundException {
        airportService.remove(id);
    }
}
