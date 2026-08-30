package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findAirportById(Long id) {
        return airportRepository.findById(id).get();
    }

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport, Long id) {
        Airport savedAirport = airportRepository.findById(id).get();

        if(Objects.nonNull(airport.getName()) && !"".equalsIgnoreCase(airport.getName())) {
            savedAirport.setName(airport.getName());
        }

        if(Objects.nonNull(airport.getAddress()) && !"".equalsIgnoreCase(airport.getAddress())) {
            savedAirport.setAddress(airport.getAddress());
        }

        if(Objects.nonNull(airport.getCity()) && !"".equalsIgnoreCase(airport.getCity())) {
            savedAirport.setCity(airport.getCity());
        }

        if(Objects.nonNull(airport.getIcao()) && !"".equalsIgnoreCase(airport.getIcao())) {
            savedAirport.setIcao(airport.getIcao());
        }

        return airportRepository.save(savedAirport);
    }

    @Override
    public void remove(Long id) {
        airportRepository.deleteById(id);
    }
}
