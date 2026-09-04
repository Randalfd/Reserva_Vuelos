package com.gutierrez.reserva_vuelos.backend.error.dto;

import org.springframework.http.HttpStatus;

public record ErrorDto(HttpStatus httpStatus, String message) {
}
