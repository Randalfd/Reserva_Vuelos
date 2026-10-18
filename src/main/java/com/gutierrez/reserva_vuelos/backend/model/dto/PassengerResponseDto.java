package com.gutierrez.reserva_vuelos.backend.model.dto;

public record PassengerResponseDto(
        String firstname,
        String lastname,
        String email,
        int dni
) {}
