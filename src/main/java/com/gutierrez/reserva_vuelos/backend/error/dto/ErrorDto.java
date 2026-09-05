package com.gutierrez.reserva_vuelos.backend.error.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorDto(Instant timestamp,
                       HttpStatus status,
                       String error,
                       String message,
                       String path) { }
