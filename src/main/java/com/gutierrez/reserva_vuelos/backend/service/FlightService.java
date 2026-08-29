package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> findAllFlights();
}
