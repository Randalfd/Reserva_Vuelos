package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.respository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public List<Passenger> findAllPassenger() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findPassengerById(Long id) {
        return passengerRepository.findById(id).get();
    }

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger, Long id) {
        Passenger savedPassenger = passengerRepository.findById(id).get();

        if(Objects.nonNull(passenger.getFirstname()) && !"".equalsIgnoreCase(passenger.getFirstname())) {
            savedPassenger.setFirstname(passenger.getFirstname());
        }

        if(Objects.nonNull(passenger.getLastname()) && !"".equalsIgnoreCase(passenger.getLastname())) {
            savedPassenger.setLastname(passenger.getLastname());
        }

        if(Objects.nonNull(passenger.getEmail()) && !"".equalsIgnoreCase(passenger.getEmail())) {
            savedPassenger.setEmail(passenger.getEmail());
        }

        return passengerRepository.save(savedPassenger);
    }

    @Override
    public void removePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
