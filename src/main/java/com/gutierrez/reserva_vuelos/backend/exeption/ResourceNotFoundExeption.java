package com.gutierrez.reserva_vuelos.backend.exeption;

public class ResourceNotFoundExeption extends RuntimeException {
    public ResourceNotFoundExeption(String message) {
        super(message);
    }
}
