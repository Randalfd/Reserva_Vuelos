package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("/api/passenger")
    public List<Passenger> findAllPassenger() {
        return passengerService.findAllPassenger();
    }

    @GetMapping("/api/passenger/{id}")
    public Passenger findPassengerById(@PathVariable Long id) {
        return passengerService.findPassengerById(id);
    }

    @PostMapping("/api/passenger")
    public Passenger savePassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    @PutMapping("/api/passenger/{id}")
    public Passenger updatePassenger(@RequestBody Passenger passenger, @PathVariable Long id) {
        return passengerService.updatePassenger(passenger, id);
    }

    @DeleteMapping("/api/passenger/{id}")
    public void removePassenger(@PathVariable Long id) {
        passengerService.removePassenger(id);
    }
}
