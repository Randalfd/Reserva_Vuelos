package com.gutierrez.reserva_vuelos.backend.respository;

import com.gutierrez.reserva_vuelos.backend.model.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByName(String name);

    @Query("""
        SELECT DISTINCT a
        FROM Airline a
        LEFT JOIN FETCH a.mainAirport
        LEFT JOIN FETCH a.airports
        """)
    List<Airline> findAllWithAirports();
}
