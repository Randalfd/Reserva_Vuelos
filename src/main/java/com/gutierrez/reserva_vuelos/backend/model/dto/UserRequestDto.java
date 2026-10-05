package com.gutierrez.reserva_vuelos.backend.model.dto;

import com.gutierrez.reserva_vuelos.backend.model.enums.Role;

public record UserRequestDto(
        String firstname,
        String lastname,
        String email,
        String password,
        Role role
) {
}
