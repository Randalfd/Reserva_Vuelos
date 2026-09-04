package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerDto;
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
    public List<PassengerDto> findAllPassenger() {
        return passengerService.findAllPassenger();
    }

    @GetMapping("/api/passenger/{id}")
    public PassengerDto findPassengerById(@PathVariable Long id) throws ResourceNotFoundException {
        return passengerService.findPassengerById(id);
    }

    @PostMapping("/api/passenger")
    public PassengerDto savePassenger(@RequestBody PassengerDto passenger) {
        return passengerService.savePassenger(passenger);
    }

    @PutMapping("/api/passenger/{id}")
    public PassengerDto updatePassenger(@RequestBody PassengerDto passenger, @PathVariable Long id) throws ResourceNotFoundException {
        return passengerService.updatePassenger(passenger, id);
    }

    @DeleteMapping("/api/passenger/{id}")
    public void removePassenger(@PathVariable Long id) throws ResourceNotFoundException {
        passengerService.removePassenger(id);
    }
}