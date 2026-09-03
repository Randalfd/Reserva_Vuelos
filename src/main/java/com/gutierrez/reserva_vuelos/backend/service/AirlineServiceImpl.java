package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.mapper.AirlineMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import com.gutierrez.reserva_vuelos.backend.respository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository airlineRepository;

    @Autowired
    AirlineMapper airlineMapper;

    @Override
    public List<AirlineDto> findAllAirlines() {
        List<AirlineDto> airlineDtos = airlineRepository.findAll().
                stream().
                map( airline -> airlineMapper.airlineToAirlineDto(airline))
                .collect(Collectors.toList());
        return airlineDtos;
    }

    @Override
    public AirlineDto findAirlineById(Long id) {
       return airlineMapper.airlineToAirlineDto(airlineRepository.findById(id).get());
    }

    @Override
    public AirlineDto saveAirline(AirlineDto airlineDto) {
        Airline airline = airlineMapper.airlineDtoToAirline(airlineDto);
        return airlineMapper.airlineToAirlineDto(airlineRepository.save(airline));
    }

    @Override
    public AirlineDto updateAirline(AirlineDto airlineDto, Long id) {
        Airline savedAirline = airlineRepository.findById(id).get();
        Airline airline = airlineMapper.airlineDtoToAirline(airlineDto);

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
    public void removeAirline(Long id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public Optional<AirlineDto> findByName(String name) {
        return Optional.ofNullable(airlineMapper.airlineToAirlineDto(airlineRepository.findByName(name).get()));
    }
}
