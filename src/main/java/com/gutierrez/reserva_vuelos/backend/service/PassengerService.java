package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAllPassenger();
    Passenger findPassengerById(Long id);
    Passenger savePassenger(Passenger passenger);
    Passenger updatePassenger(Passenger passenger, Long id);
    void removePassenger(Long id);
}
