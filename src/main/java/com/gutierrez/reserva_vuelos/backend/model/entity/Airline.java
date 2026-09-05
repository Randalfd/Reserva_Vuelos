package com.gutierrez.reserva_vuelos.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airlines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Airline name must be provided")
    private String name;
    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;
    @Email
    @NotBlank(message = "Airline email must be provided")
    private String email;
    private String phone;
}
