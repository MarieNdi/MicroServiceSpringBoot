package com.isi.school_management.user_service.exception;

public class UserAlreadyAssignedException extends RuntimeException {
    public UserAlreadyAssignedException(String message) {
        super("L'utilisateur est déjà assigné: " + message);
    }
} 