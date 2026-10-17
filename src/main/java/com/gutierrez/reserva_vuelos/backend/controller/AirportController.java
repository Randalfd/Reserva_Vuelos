package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping("/api/airport")
    @PreAuthorize("isAuthenticated()")
    public List<AirportDto> findAllAirports() {
        return airportService.findAllAirports();
    }

    @GetMapping("/api/airport/{id}")
    @PreAuthorize("isAuthenticated()")
    public AirportDto findAirportById(@PathVariable Long id) throws ResourceNotFoundException {
        return airportService.findAirportById(id);
    }

    @PostMapping("/api/airport")
    @PreAuthorize("hasRole('ADMIN')")
    public AirportDto saveAirport(@Valid @RequestBody AirportDto airportDto) {
        return airportService.saveAirport(airportDto);
    }

    @PutMapping("/api/airport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AirportDto updateAirport(@Valid @RequestBody AirportDto airport, @PathVariable Long id) throws ResourceNotFoundException {
        return airportService.updateAirport(airport, id);
    }

    @DeleteMapping("/api/airport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAirport(@PathVariable Long id) throws ResourceNotFoundException {
        airportService.remove(id);
    }
}
