package com.gutierrez.reserva_vuelos.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @ManyToOne
   @JoinColumn(name = "origin_airport_id")
   private Airport origin_airport;
   @ManyToOne
   @JoinColumn(name = "destination_airport_id")
   private Airport destination_airport;
   private double amount;
   private LocalDate departure;
   private LocalDate arrive;
   @ManyToOne
   @JoinColumn(name = "airline_id")
   private Airline airline;

}
