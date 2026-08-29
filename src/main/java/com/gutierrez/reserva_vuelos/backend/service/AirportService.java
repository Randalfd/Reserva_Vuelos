package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAllAirports();

}
