package com.gutierrez.reserva_vuelos.backend.error;

import com.gutierrez.reserva_vuelos.backend.error.dto.ErrorDto;
import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> ResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorDto errorDto = new ErrorDto(
                Instant.now(),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                path);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().
                forEach(error -> {
                    fieldErrors.put(error.getField(), error.getDefaultMessage());
                });

        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorDto errorDto = new ErrorDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                fieldErrors.toString(),
                path);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
