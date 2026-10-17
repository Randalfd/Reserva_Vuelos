package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import com.gutierrez.reserva_vuelos.backend.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@EnableMethodSecurity
public class FlightController {
  private static int pageSize = 3;

  @Autowired
  FlightService flightService;

  // CRUD Endpoints
  @GetMapping("/api/flight")
  @PreAuthorize("isAuthenticated()")
  public List<FlightResponseDto> getAllFlights() {
    return flightService.findAllFlights();
  }

  @GetMapping("/api/flight/{id:\\d+}")
  @PreAuthorize("isAuthenticated()")
  public FlightResponseDto getFlightById(@PathVariable Long id) throws ResourceNotFoundException {
    return flightService.findFlightById(id);
  }

  @PostMapping("/api/flight")
  @PreAuthorize("hasRole('ADMIN')")
  public FlightResponseDto saveFlight(@Valid @RequestBody FlightRequestDto flightRequestDto) throws ResourceNotFoundException {
    return flightService.saveFlight(flightRequestDto);
  }

  @PutMapping("/api/fligth/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public FlightResponseDto updateFlight(@Valid @RequestBody FlightRequestDto flightRequestDto, @PathVariable Long id) throws ResourceNotFoundException {
    return flightService.updateFlight(flightRequestDto, id);
  }

  @DeleteMapping("/api/flight/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void removeFlight(@PathVariable Long id) throws ResourceNotFoundException {
    flightService.removeFlight(id);
  }

  // Custom Endpoints
  @GetMapping("/api/flight/sort")
  @PreAuthorize("isAuthenticated()")
  public List<FlightResponseDto> findByOrderByDepartureDesc() {
    return flightService.findByOrderByDepartureDesc();
  }

  @GetMapping("/api/flight/findByDepartureBetween")
  @PreAuthorize("isAuthenticated()")
  public Page<FlightResponseDto> findByDepartureBetween(@RequestParam LocalDate departureFrom, @RequestParam LocalDate departureTo, @RequestParam(defaultValue = "0") int index) {
    return flightService.findByDepartureBetween(departureFrom, departureTo, index, pageSize);
  }

  @GetMapping("/api/flight/findByArrivalAfter")
  @PreAuthorize("isAuthenticated()")
  public Page<FlightResponseDto> findByArrivalAfter(@RequestParam LocalDate arrival, @RequestParam(defaultValue = "0") int index) {
    return flightService.findByArrivalAfter(arrival, index, pageSize);
  }
}
