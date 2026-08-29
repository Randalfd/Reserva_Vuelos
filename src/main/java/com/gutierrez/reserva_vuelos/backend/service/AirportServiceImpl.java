package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }
}
