package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.FlightMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.FlightResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import com.gutierrez.reserva_vuelos.backend.respository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public List<FlightResponseDto> findAllFlights() {
    return flightRepository.findAll().
            stream().
            map(flightMapper::flightToFlightResponseDto).
            toList();
  }

  @Override
  public FlightResponseDto findFlightById(Long id) throws ResourceNotFoundException {
    return flightRepository.findById(id).
            map(flightMapper::flightToFlightResponseDto).
            orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));
  }

  @Override
  public FlightResponseDto saveFlight(FlightRequestDto flightRequestDto) throws ResourceNotFoundException {
    Flight flight = flightMapper.flightDtoToFlight(flightRequestDto);

    Airline airline = airlineRepository.findById(flightRequestDto.airlineId()).
            orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
    flight.setAirline(airline);

    Airport originAirport = airportRepository.findById(flightRequestDto.originAirportId()).
            orElseThrow(() -> new ResourceNotFoundException("Origin Airport Not Found"));
    flight.setOriginAirport(originAirport);

    Airport destinationAirport = airportRepository.findById(flightRequestDto.destinationAirportId()).
            orElseThrow(() -> new ResourceNotFoundException("Destination Airport Not Found"));
    flight.setDestinationAirport(destinationAirport);

    return flightMapper.flightToFlightResponseDto(flightRepository.save(flight));
  }

  @Override
  public FlightResponseDto updateFlight(FlightRequestDto flightRequestDto, Long id) throws ResourceNotFoundException {
    Flight savedFlight = flightRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));

    Flight flight = flightMapper.flightDtoToFlight(flightRequestDto);

    Airline airline = airlineRepository.findById(flightRequestDto.airlineId()).
            orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
    flight.setAirline(airline);

    Airport originAirport = airportRepository.findById(flightRequestDto.originAirportId()).
            orElseThrow(() -> new ResourceNotFoundException("Origin Airport Not Found"));
    flight.setOriginAirport(originAirport);

    Airport destinationAirport = airportRepository.findById(flightRequestDto.destinationAirportId()).
            orElseThrow(() -> new ResourceNotFoundException("Destination Airport Not Found"));
    flight.setDestinationAirport(destinationAirport);


    savedFlight.setAirline(flight.getAirline());

    savedFlight.setOriginAirport(flight.getOriginAirport());

    savedFlight.setDestinationAirport(flight.getDestinationAirport());

    savedFlight.setArrival(flight.getArrival());

    savedFlight.setDeparture(flight.getDeparture());

    return flightMapper.flightToFlightResponseDto(flightRepository.save(savedFlight));
  }

  @Override
  public void removeFlight(Long id) throws ResourceNotFoundException {
    flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flight Not Found"));
    flightRepository.deleteById(id);
  }

  @Override
  public List<FlightResponseDto> findByOrderByDepartureDesc() {
    return flightRepository.findByOrderByDepartureDesc().
            stream().
            map(flightMapper::flightToFlightResponseDto).
            toList();
  }
}
