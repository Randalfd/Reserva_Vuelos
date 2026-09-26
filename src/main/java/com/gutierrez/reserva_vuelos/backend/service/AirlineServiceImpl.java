package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.AirlineMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository airlineRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    AirlineMapper airlineMapper;

    @Override
    public List<AirlineRequestDto> findAllAirlines() {
        return airlineRepository.findAll()
                .stream()
                .map(airlineMapper::airlineToAirlineRequestDto)
                .toList();
    }

    @Override
    public AirlineRequestDto findAirlineById(Long id) throws ResourceNotFoundException {
        return airlineRepository.findById(id).
                map(airlineMapper::airlineToAirlineRequestDto).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
    }

    @Override
    public AirlineRequestDto saveAirline(AirlineRequestDto airlineRequestDto) throws ResourceNotFoundException {
        Airline airline = airlineMapper.airlineRequestDtoToAirline(airlineRequestDto);
        Airport airport = airportRepository.findById(airlineRequestDto.airportId()).
                orElseThrow(() -> new ResourceNotFoundException("Airport not Found"));

        airline.setMainAirport(airport);
        return airlineMapper.airlineToAirlineRequestDto(airlineRepository.save(airline));
    }

    @Override
    public AirlineRequestDto updateAirline(AirlineRequestDto airlineRequestDto, Long id) throws ResourceNotFoundException {
        Airline savedAirline = airlineRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));

        Airline airline = airlineMapper.airlineRequestDtoToAirline(airlineRequestDto);

        Airport airport = airportRepository.findById(airlineRequestDto.airportId()).
                orElseThrow(() -> new ResourceNotFoundException("Airport Not Found"));
        airline.setMainAirport(airport);

        if (Objects.nonNull(airline.getMainAirport())) {
            savedAirline.setMainAirport(airline.getMainAirport());
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

        return airlineMapper.airlineToAirlineRequestDto(airlineRepository.save(savedAirline));
    }

    @Override
    public void removeAirline(Long id) throws ResourceNotFoundException {
        airlineRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
        airlineRepository.deleteById(id);
    }

    @Override
    public AirlineRequestDto findByName(String name) throws ResourceNotFoundException {
        Airline airline = airlineRepository.findByName(name).
                orElseThrow(() -> new ResourceNotFoundException("Airline Not Found"));
        return airlineMapper.airlineToAirlineRequestDto(airline);
    }
}