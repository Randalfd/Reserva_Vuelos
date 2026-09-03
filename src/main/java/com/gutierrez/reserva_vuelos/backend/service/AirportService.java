package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;

import java.util.List;

public interface AirportService {
    List<AirportDto> findAllAirports();
    AirportDto findAirportById(Long id);
    AirportDto saveAirport(AirportDto airport);
    AirportDto updateAirport(AirportDto airport, Long id);
    void remove(Long id);

}
