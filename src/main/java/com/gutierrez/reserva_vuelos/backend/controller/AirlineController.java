package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    // CRUD Endpoints
    @GetMapping("/api/airline")
    public List<AirlineDto> findAllAirlines() {
        return airlineService.findAllAirlines();
    }

    @GetMapping("/api/airline/{id}")
    public AirlineDto findAirlineById(@PathVariable Long id) {
        return airlineService.findAirlineById(id);
    }

    @PostMapping("/api/airline")
    public AirlineDto saveAirline(@RequestBody AirlineDto airlineDto) {
        return airlineService.saveAirline(airlineDto);
    }

    @PutMapping("/api/airline/{id}")
    public AirlineDto updateAirline(@RequestBody AirlineDto airlineDto, @PathVariable Long id) {
        return airlineService.updateAirline(airlineDto, id);
    }

    @DeleteMapping("/api/airline/{id}")
    public void removeAirline(@PathVariable Long id) {
        airlineService.removeAirline(id);
    }

    // Custom Endpoints
    @GetMapping("/api/airline/search{name}")
    public Optional<AirlineDto> findByName(@RequestParam String name) {
        return airlineService.findByName(name);
    }
}
