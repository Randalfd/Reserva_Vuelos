package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.AirlineMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class AirlineServiceImpl implements AirlineService {

  @Autowired
  AirlineRepository airlineRepository;

  @Autowired
  AirportRepository airportRepository;

  @Autowired
  AirlineMapper airlineMapper;

  @Override
  public List<AirlineResponseDto> findAllAirlines() {
    return airlineRepository.findAll()
            .stream()
            .map(airlineMapper::airlineToAirlineResponseDto)
            .toList();
  }

  @Override
  public AirlineResponseDto findAirlineById(Long id) throws ResourceNotFoundException {
    return airlineRepository.findById(id).
            map(airlineMapper::airlineToAirlineResponseDto).
            orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
  }

  @Override
  public AirlineResponseDto saveAirline(AirlineRequestDto airlineRequestDto) throws ResourceNotFoundException {
    Airline airline = airlineMapper.airlineRequestDtoToAirline(airlineRequestDto);
    Airport airport = airportRepository.findById(airlineRequestDto.mainAirportId()).
            orElseThrow(() -> new ResourceNotFoundException("Airport not Found"));

    airline.setMainAirport(airport);

    Set<Airport> airports = new HashSet<>(airportRepository.findAllById(airlineRequestDto.airportsIds()));
    if(airports.size() != airlineRequestDto.airportsIds().size()) throw new ResourceNotFoundException("One or more airports not found");
    airline.setAirports(airports);

    return airlineMapper.airlineToAirlineResponseDto(airlineRepository.save(airline));
  }

  @Override
  public AirlineResponseDto updateAirline(AirlineRequestDto airlineRequestDto, Long id) throws ResourceNotFoundException {
    Airline savedAirline = airlineRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));

    Airline airline = airlineMapper.airlineRequestDtoToAirline(airlineRequestDto);

    Airport airport = airportRepository.findById(airlineRequestDto.mainAirportId()).
            orElseThrow(() -> new ResourceNotFoundException("Airport Not Found"));
    airline.setMainAirport(airport);

    // validaciones desde el lado del requestDto
    savedAirline.setMainAirport(airline.getMainAirport());
    savedAirline.setName(airline.getName());
    savedAirline.setEmail(airline.getEmail());
    savedAirline.setPhone(airline.getPhone());

    return airlineMapper.airlineToAirlineResponseDto(airlineRepository.save(savedAirline));
  }

  @Override
  public void removeAirline(Long id) throws ResourceNotFoundException {
    airlineRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
    airlineRepository.deleteById(id);
  }

  @Override
  public AirlineResponseDto findByName(String name) throws ResourceNotFoundException {
    Airline airline = airlineRepository.findByName(name).
            orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
    return airlineMapper.airlineToAirlineResponseDto(airline);
  }
}