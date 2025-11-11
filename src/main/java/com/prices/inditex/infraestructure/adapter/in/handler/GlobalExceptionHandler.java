package com.prices.inditex.infraestructure.adapter.in.handler;

import com.prices.inditex.domain.exception.NotFoundException;
import com.prices.inditex.domain.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePriceNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, 404,
                ERROR, "Price Not Found",
                MESSAGE, ex.getMessage()
        ));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequest(InvalidRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, 400,
                ERROR, "Invalid Request",
                MESSAGE, ex.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, 400,
                ERROR, "Invalid Parameter Format",
                MESSAGE, "El formato de un parámetro es incorrecto. Revisa la fecha o los IDs enviados."
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                TIMESTAMP, LocalDateTime.now(),
                STATUS, 500,
                ERROR, "Internal Server Error",
                MESSAGE, ex.getMessage()
        ));
    }
}