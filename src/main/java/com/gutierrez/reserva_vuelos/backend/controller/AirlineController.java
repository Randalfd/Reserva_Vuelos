package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineResponseDto;
import com.gutierrez.reserva_vuelos.backend.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    // CRUD Endpoints
    @GetMapping("/api/airline")
    @PreAuthorize("isAuthenticated()")
    public List<AirlineResponseDto> findAllAirlines() {
        return airlineService.findAllAirlines();
    }

    @GetMapping("/api/airline/{id}")
    @PreAuthorize("isAuthenticated()")
    public AirlineResponseDto findAirlineById(@PathVariable Long id) throws ResourceNotFoundException{
        return airlineService.findAirlineById(id);
    }

    @PostMapping("/api/airline")
    @PreAuthorize("hasRole('ADMIN')")
    public AirlineResponseDto saveAirline(@Valid @RequestBody AirlineRequestDto airlineRequestDto) throws ResourceNotFoundException {
        return airlineService.saveAirline(airlineRequestDto);
    }

    @PutMapping("/api/airline/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AirlineResponseDto updateAirline(@Valid @RequestBody AirlineRequestDto airlineRequestDto, @PathVariable Long id) throws ResourceNotFoundException{
        return airlineService.updateAirline(airlineRequestDto, id);
    }

    @DeleteMapping("/api/airline/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeAirline(@PathVariable Long id) throws ResourceNotFoundException {
        airlineService.removeAirline(id);
    }

    // Custom Endpoints
    @GetMapping("/api/airline/search{name}")
    @PreAuthorize("isAuthenticated()")
    public AirlineResponseDto findByName(@RequestParam String name) throws ResourceNotFoundException {
        return airlineService.findByName(name);
    }
}
