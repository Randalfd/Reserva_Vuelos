package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.FlightInvalidAmountExeption;
import com.gutierrez.reserva_vuelos.backend.exeption.FlightInvalidDateExeption;
import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundExeption;
import com.gutierrez.reserva_vuelos.backend.mapper.FlightMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
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

    @Autowired
    FlightMapper flightMapper;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public List<FlightDto> findAllFlights() {
        return flightRepository.findAll().
                stream().
                map(flightMapper::flightToFlightDto).
                toList();
    }

    @Override
    public FlightDto findFlightById(Long id) {
        return flightRepository.findById(id).map(flightMapper::flightToFlightDto).orElseThrow(() -> new ResourceNotFoundExeption("Flight Not Found"));
    }

    @Override
    public FlightDto saveFlight(FlightDto flightDto) {
        Flight flight = flightMapper.flightDtoToFlight(flightDto);

        flight.setAirline(airlineRepository.findById(flightDto.airlineId()).orElseThrow(() -> new ResourceNotFoundExeption("Airline Not Found")));
        flight.setOriginAirport(airportRepository.findById(flightDto.originAirportId()).orElseThrow(() -> new ResourceNotFoundExeption("Origin Airport Not Found")));
        flight.setDestinationAirport(airportRepository.findById(flightDto.destinationAirportId()).orElseThrow(() -> new ResourceNotFoundExeption("Destination Airport Not Found")));

        return flightMapper.flightToFlightDto(flightRepository.save(flight));
    }

    @Override
    public FlightDto updateFlight(FlightDto flightDto, Long id) {
        Flight savedFlight = flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Flight Not Found"));
        Flight flight = flightMapper.flightDtoToFlight(flightDto);
        flight.setAirline(airlineRepository.findById(flightDto.airlineId()).orElseThrow(() -> new ResourceNotFoundExeption("Airline Not Found")));
        flight.setOriginAirport(airportRepository.findById(flightDto.originAirportId()).orElseThrow(() -> new ResourceNotFoundExeption("Origin Airport Not Found")));
        flight.setDestinationAirport(airportRepository.findById(flightDto.destinationAirportId()).orElseThrow(() -> new ResourceNotFoundExeption("Destination Airport Not Found")));

        if(Objects.nonNull(flight.getAirline())) {
            savedFlight.setAirline(flight.getAirline());
        }

        if(Objects.nonNull(flight.getOriginAirport())) {
            savedFlight.setOriginAirport(flight.getOriginAirport());
        }

        if(Objects.nonNull(flight.getDestinationAirport())) {
            savedFlight.setDestinationAirport(flight.getDestinationAirport());
        }

        if(Objects.nonNull(flight.getArrival())) {
            savedFlight.setArrival(flight.getArrival());
        }

        if(Objects.nonNull(flight.getDeparture())) {
            savedFlight.setDeparture(flight.getDeparture());
        }

        return flightMapper.flightToFlightDto(flightRepository.save(flight));
    }

    @Override
    public void removeFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDto> findByOrderByDepartureDesc() {
        return flightRepository.findByOrderByDepartureDesc();
    }
}
