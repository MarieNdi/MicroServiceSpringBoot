package com.isi.school_management.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyAssignedException.class)
    public ResponseEntity<String> handleUserAlreadyAssignedException(UserAlreadyAssignedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Autres gestionnaires d'exceptions si nécessaire...
}