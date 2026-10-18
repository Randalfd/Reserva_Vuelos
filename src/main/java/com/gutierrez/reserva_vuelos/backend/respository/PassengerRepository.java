package com.gutierrez.reserva_vuelos.backend.respository;

import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
  List<Passenger> findByFirstnameLike(String pattern);
}
