package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    // CRUD Endpoints
    @GetMapping("/api/airline")
    public List<AirlineRequestDto> findAllAirlines() {
        return airlineService.findAllAirlines();
    }

    @GetMapping("/api/airline/{id}")
    public AirlineRequestDto findAirlineById(@PathVariable Long id) throws ResourceNotFoundException{
        return airlineService.findAirlineById(id);
    }

    @PostMapping("/api/airline")
    public AirlineRequestDto saveAirline(@Valid @RequestBody AirlineRequestDto airlineRequestDto) throws ResourceNotFoundException {
        return airlineService.saveAirline(airlineRequestDto);
    }

    @PutMapping("/api/airline/{id}")
    public AirlineRequestDto updateAirline(@Valid @RequestBody AirlineRequestDto airlineRequestDto, @PathVariable Long id) throws ResourceNotFoundException{
        return airlineService.updateAirline(airlineRequestDto, id);
    }

    @DeleteMapping("/api/airline/{id}")
    public void removeAirline(@PathVariable Long id) throws ResourceNotFoundException {
        airlineService.removeAirline(id);
    }

    // Custom Endpoints
    @GetMapping("/api/airline/search{name}")
    public AirlineRequestDto findByName(@RequestParam String name) throws ResourceNotFoundException {
        return airlineService.findByName(name);
    }
}
