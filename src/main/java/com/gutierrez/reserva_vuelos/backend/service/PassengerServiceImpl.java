package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.respository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassengerServiceImpl implements PassengerService{

    @Autowired
    PassengerRepository passengerRepository;
    @Override
    public List<Passenger> findAllPassenger() {
        return passengerRepository.findAll();
    }
}
