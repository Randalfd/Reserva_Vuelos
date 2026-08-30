package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlightService {
    List<Flight> findAllFlights();
    Flight findFlightById(Long id);
    Flight saveFlight(Flight flight);
    Flight updateFlight(Flight flight, Long id);
    void removeFlight(Long id);
}
