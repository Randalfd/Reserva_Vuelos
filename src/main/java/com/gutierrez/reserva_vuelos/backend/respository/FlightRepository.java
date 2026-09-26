package com.gutierrez.reserva_vuelos.backend.respository;

import com.gutierrez.reserva_vuelos.backend.model.dto.FlightRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<FlightRequestDto> findByOrderByDepartureDesc();
}
