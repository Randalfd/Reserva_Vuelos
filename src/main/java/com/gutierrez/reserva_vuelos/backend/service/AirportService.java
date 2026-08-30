package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAllAirports();
    Airport findAirportById(Long id);
    Airport saveAirport(Airport airport);
    Airport updateAirport(Airport airport, Long id);
    void remove(Long id);

}
