package com.gutierrez.reserva_vuelos.backend.exeption;

public class FlightInvalidDateExeption extends RuntimeException {
    public FlightInvalidDateExeption(String message) {
        super(message);
    }
}
