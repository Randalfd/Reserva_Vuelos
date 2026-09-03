package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.AirlineDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    List<AirlineDto> findAllAirlines();
    AirlineDto findAirlineById(Long id);
    AirlineDto saveAirline(AirlineDto airlineDto);
    AirlineDto updateAirline(AirlineDto airlineDto, Long id);
    void removeAirline(Long id);
    Optional<AirlineDto> findByName(String name);

}
