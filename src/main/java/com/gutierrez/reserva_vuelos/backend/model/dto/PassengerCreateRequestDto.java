package com.gutierrez.reserva_vuelos.backend.model.dto;

import jakarta.validation.constraints.*;

public record PassengerCreateRequestDto(
        @NotBlank(message = "Passenger firstname must not be blank") String firstname,
        @NotBlank(message = "Passenger lastname must not be blank") String lastname,
        @Email @NotBlank(message = "Passenger email must not be blank") String email,
        @NotBlank @Size(min = 8) String password,
        @NotNull(message = "Passenger dni must be provided") @Positive int dni
) {}
