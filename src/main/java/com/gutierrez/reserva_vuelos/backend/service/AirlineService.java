package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    List<Airline> findAllAirlines();
    Airline findAirlineById(Long id);
    Airline saveAirline(Airline airline);
    Airline updateAirline(Airline airline, Long id);
    void removeAirline(Long id);
    Optional<Airline> findByName(String name);

}
