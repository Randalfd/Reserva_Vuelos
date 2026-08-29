package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAllPassenger();
}
