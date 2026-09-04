package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.AirlineMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository airlineRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    AirlineMapper airlineMapper;

    @Override
    public List<AirlineDto> findAllAirlines() {
        return airlineRepository.findAll()
                .stream()
                .map(airlineMapper::airlineToAirlineDto)
                .toList();
    }

    @Override
    public AirlineDto findAirlineById(Long id) throws ResourceNotFoundException {
        return airlineRepository.findById(id).
                map(airlineMapper::airlineToAirlineDto).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
    }

    @Override
    public AirlineDto saveAirline(AirlineDto airlineDto) throws ResourceNotFoundException {
        Airline airline = airlineMapper.airlineDtoToAirline(airlineDto);
        Airport airport = airportRepository.findById(airlineDto.airportId()).
                orElseThrow(() -> new ResourceNotFoundException("Airport not Found"));

        airline.setAirport(airport);
        return airlineMapper.airlineToAirlineDto(airlineRepository.save(airline));
    }

    @Override
    public AirlineDto updateAirline(AirlineDto airlineDto, Long id) throws ResourceNotFoundException {
        Airline savedAirline = airlineRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));

        Airline airline = airlineMapper.airlineDtoToAirline(airlineDto);

        Airport airport = airportRepository.findById(airlineDto.airportId()).
                orElseThrow(() -> new ResourceNotFoundException("Airport Not Found"));
        airline.setAirport(airport);

        if (Objects.nonNull(airline.getAirport())) {
            savedAirline.setAirport(airline.getAirport());
        }

        if (Objects.nonNull(airline.getName()) && !"".equalsIgnoreCase(airline.getName())) {
            savedAirline.setName(airline.getName());
        }

        if (Objects.nonNull(airline.getEmail()) && !"".equalsIgnoreCase(airline.getEmail())) {
            savedAirline.setEmail(airline.getEmail());
        }

        if (Objects.nonNull(airline.getPhone()) && !"".equalsIgnoreCase(airline.getPhone())) {
            savedAirline.setPhone(airline.getPhone());
        }

        return airlineMapper.airlineToAirlineDto(airlineRepository.save(savedAirline));
    }

    @Override
    public void removeAirline(Long id) throws ResourceNotFoundException {
        airlineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
        airlineRepository.deleteById(id);
    }

    @Override
    public AirlineDto findByName(String name) throws ResourceNotFoundException {
        Airline airline = airlineRepository.findByName(name).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
        return airlineMapper.airlineToAirlineDto(airline);
    }
}