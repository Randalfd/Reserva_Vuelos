package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.FlightInvalidAmountExeption;
import com.gutierrez.reserva_vuelos.backend.exeption.FlightInvalidDateExeption;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.respository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).get();
    }

    @Override
    public Flight saveFlight(Flight flight) {
        LocalDate today = LocalDate.now();

        if(flight.getArrival().isBefore(today)) {
            throw new FlightInvalidDateExeption("La fecha de llegada no puede ser menor a la fecha actual");
        }

        if(flight.getDeparture().isBefore(today)) {
            throw new FlightInvalidDateExeption("La fecha de partida no puede ser menor a la fecha actual");
        }

        if(flight.getAmount() < 0) {
            throw new FlightInvalidAmountExeption("El monto no puede ser menor 0");
        }

        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight, Long id) {
        Flight savedFlight = flightRepository.findById(id).get();

        LocalDate today = LocalDate.now();

        if(flight.getArrival().isBefore(today)) {
            throw new FlightInvalidDateExeption("La fecha de llegada no puede ser menor a la fecha actual");
        }

        if(flight.getDeparture().isBefore(today)) {
            throw new FlightInvalidDateExeption("La fecha de partida no puede ser menor a la fecha actual");
        }

        if(flight.getAmount() < 0) {
            throw new FlightInvalidAmountExeption("El monto no puede ser menor 0");
        }

        if(Objects.nonNull(flight.getAirline())) {
            savedFlight.setAirline(flight.getAirline());
        }

        if(Objects.nonNull(flight.getOrigin_airport())) {
            savedFlight.setOrigin_airport(flight.getOrigin_airport());
        }

        if(Objects.nonNull(flight.getDestination_airport())) {
            savedFlight.setDestination_airport(flight.getDestination_airport());
        }

        if(Objects.nonNull(flight.getArrival())) {
            savedFlight.setArrival(flight.getArrival());
        }

        if(Objects.nonNull(flight.getDeparture())) {
            savedFlight.setDeparture(flight.getDeparture());
        }

        return flightRepository.save(savedFlight);
    }

    @Override
    public void removeFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
