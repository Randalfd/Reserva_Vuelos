package com.gutierrez.reserva_vuelos.backend.exeption;

public class FlightInvalidAmountExeption extends RuntimeException {
    public FlightInvalidAmountExeption(String message) {
        super(message);
    }
}
