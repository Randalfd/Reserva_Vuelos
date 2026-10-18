package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerCreateRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerUpdateRequestDto;
import com.gutierrez.reserva_vuelos.backend.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("/api/passenger")
    @PreAuthorize("isAuthenticated()")
    public List<PassengerResponseDto> findAllPassenger() {
        return passengerService.findAllPassenger();
    }

    @GetMapping("/api/passenger/{id}")
    @PreAuthorize("isAuthenticated()")
    public PassengerResponseDto findPassengerById(@PathVariable Long id) throws ResourceNotFoundException {
        return passengerService.findPassengerById(id);
    }

    @PostMapping("/api/passenger")
    @PreAuthorize("hasRole('ADMIN')")
    public PassengerResponseDto savePassenger(@Valid @RequestBody PassengerCreateRequestDto passenger) {
        return passengerService.savePassenger(passenger);
    }

    @PutMapping("/api/passenger/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public PassengerResponseDto updatePassenger(@Valid @RequestBody PassengerUpdateRequestDto passenger, @PathVariable Long id) throws ResourceNotFoundException {
        return passengerService.updatePassenger(passenger, id);
    }

    @DeleteMapping("/api/passenger/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void removePassenger(@PathVariable Long id) throws ResourceNotFoundException {
        passengerService.removePassenger(id);
    }

    @GetMapping("/api/passenger/findByNameLike/{name}")
    @PreAuthorize("isAuthenticated()")
    public List<PassengerResponseDto> findByFirstNameLike(@PathVariable String name) {
        return passengerService.findByFirstnameLike(name);
    }
}