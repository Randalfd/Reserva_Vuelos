package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerDto(
        @NotNull(message ="Passenger firstname must be provided" )
        @NotBlank(message = "Passenger firstname must not be blank")
        String firstname,
        @NotNull(message = "Passenger lastname must be provided")
        @NotBlank(message = "Passenger lastname must not  be blank")
        String lastname,
        @Email
        @NotNull(message = "Passenger email must be provided")
        @NotBlank(message = "Passenger email must not be blank")
        String email) {}
