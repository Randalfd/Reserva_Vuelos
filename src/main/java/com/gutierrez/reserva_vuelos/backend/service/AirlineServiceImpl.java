package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public List<Airline> findAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline findAirlineById(Long id) {
        return airlineRepository.findById(id).get();
    }

    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Airline updateAirline(Airline airline, Long id) {
        Airline savedAirline = airlineRepository.findById(id).get();

        if (Objects.nonNull(airline.getAirport())) {
            savedAirline.setAirport(airline.getAirport());
        }

        if (Objects.nonNull(airline.getName()) && !"".equalsIgnoreCase(airline.getName())) {
            savedAirline.setName(airline.getName());
        }

        return airlineRepository.save(savedAirline);
    }

    @Override
    public void removeAirline(Long id) {
        airlineRepository.deleteById(id);
    }
}
