package com.gutierrez.reserva_vuelos.backend.model.dto;

import com.gutierrez.reserva_vuelos.backend.model.enums.Role;

public record UserResponseDto(
        String firstname,
        String lastname,
        String email,
        Role role
) {
}
