package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.AirportMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.AirportDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airport;
import com.gutierrez.reserva_vuelos.backend.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirportServiceImpl implements AirportService {
  @Autowired
  AirportRepository airportRepository;

  @Autowired
  AirportMapper airportMapper;

  @Override
  public List<AirportDto> findAllAirports() {
    return airportRepository.findAll().
            stream().
            map(airportMapper::airportToAriportDTO).
            toList();
  }

  @Override
  public AirportDto findAirportById(Long id) throws ResourceNotFoundException {
    return airportRepository.findById(id).
            map(airportMapper::airportToAriportDTO).
            orElseThrow(() -> new ResourceNotFoundException("Airport Not Found"));
  }

  @Override
  public AirportDto saveAirport(AirportDto airportDto) {
    Airport airport = airportMapper.airportDtoToAirport(airportDto);
    return airportMapper.airportToAriportDTO(airportRepository.save(airport));
  }

  @Override
  public AirportDto updateAirport(AirportDto airportDto, Long id) throws ResourceNotFoundException {
    Airport savedAirport = airportRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Airport Not Found"));

    Airport airport = airportMapper.airportDtoToAirport(airportDto);

    savedAirport.setName(airport.getName());

    savedAirport.setAddress(airport.getAddress());

    savedAirport.setCity(airport.getCity());

    savedAirport.setIcao(airport.getIcao());

    return airportMapper.airportToAriportDTO(airportRepository.save(savedAirport));
  }

  @Override
  public void remove(Long id) throws ResourceNotFoundException {
    airportRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Airport Not Found"));
    airportRepository.deleteById(id);
  }
}
