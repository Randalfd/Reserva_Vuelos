package com.gutierrez.reserva_vuelos.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String icao;
    private String address;
    private String city;
}
