package com.gutierrez.reserva_vuelos.backend.error;

import com.gutierrez.reserva_vuelos.backend.error.dto.ErrorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedExeptionHandler implements AccessDeniedHandler {
  private final ObjectMapper objectMapper;
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    String path = request.getRequestURI();
    ErrorDto errorResponse = new ErrorDto(
            Instant.now(),
            HttpStatus.FORBIDDEN,
            HttpStatus.FORBIDDEN.getReasonPhrase(),
            "You don't have access rights",
            path
    );
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    objectMapper.writeValue(response.getOutputStream(), errorResponse);
  }
}
