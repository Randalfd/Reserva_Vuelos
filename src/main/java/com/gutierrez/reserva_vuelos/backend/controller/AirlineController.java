package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @GetMapping("/api/airline")
    public List<Airline> findAllAirlines() {
        return airlineService.findAllAirlines();
    }

    @GetMapping("/api/airline/{id}")
    public Airline findAirlineById(@PathVariable Long id) {
        return airlineService.findAirlineById(id);
    }

    @PostMapping("/api/airline")
    public Airline saveAirline(@RequestBody Airline airline) {
        return airlineService.saveAirline(airline);
    }

    @PutMapping("/api/airline/{id}")
    public Airline updateAirline(@RequestBody Airline airline, @PathVariable Long id) {
        return airlineService.updateAirline(airline, id);
    }

    @DeleteMapping("/api/airline/{id}")
    public void removeAirline(@PathVariable Long id) {
        airlineService.removeAirline(id);
    }
}
