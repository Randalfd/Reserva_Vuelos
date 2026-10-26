package com.gutierrez.reserva_vuelos.backend.respository;

import com.gutierrez.reserva_vuelos.backend.model.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOrderByDepartureDesc();

    Page<Flight> findByDepartureBetween(LocalDate start, LocalDate end, Pageable pageable);
    Page<Flight> findByArrivalAfter(LocalDate arrival, Pageable pageable);

    @EntityGraph(
            value = "flight-with-relations",
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("SELECT f FROm Flight f")
    List<Flight> findAllWithRelations();
}
