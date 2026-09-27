package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import com.gutierrez.reserva_vuelos.backend.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightController {
  private static int pageSize = 3;

  @Autowired
  FlightService flightService;

  // CRUD Endpoints
  @GetMapping("/api/flight")
  public List<FlightResponseDto> getAllFlights() {
    return flightService.findAllFlights();
  }

  @GetMapping("/api/flight/{id:\\d+}")
  public FlightResponseDto getFlightById(@PathVariable Long id) throws ResourceNotFoundException {
    return flightService.findFlightById(id);
  }

  @PostMapping("/api/flight")
  public FlightResponseDto saveFlight(@Valid @RequestBody FlightRequestDto flightRequestDto) throws ResourceNotFoundException {
    return flightService.saveFlight(flightRequestDto);
  }

  @PutMapping("/api/fligth/{id}")
  public FlightResponseDto updateFlight(@Valid @RequestBody FlightRequestDto flightRequestDto, @PathVariable Long id) throws ResourceNotFoundException {
    return flightService.updateFlight(flightRequestDto, id);
  }

  @DeleteMapping("/api/flight/{id}")
  public void removeFlight(@PathVariable Long id) throws ResourceNotFoundException {
    flightService.removeFlight(id);
  }

  // Custom Endpoints
  @GetMapping("/api/flight/sort")
  public List<FlightResponseDto> findByOrderByDepartureDesc() {
    return flightService.findByOrderByDepartureDesc();
  }

  @GetMapping("/api/flight/findByDepartureBetween")
  public Page<FlightResponseDto> findByDepartureBetween(@RequestParam LocalDate departureFrom, @RequestParam LocalDate departureTo, @RequestParam(defaultValue = "0") int index) {
    return flightService.findByDepartureBetween(departureFrom, departureTo, index, pageSize);
  }

  @GetMapping("/api/flight/findByArrivalAfter")
  public Page<FlightResponseDto> findByArrivalAfter(@RequestParam LocalDate arrival, @RequestParam(defaultValue = "0") int index) {
    return flightService.findByArrivalAfter(arrival, index, pageSize);
  }
}
