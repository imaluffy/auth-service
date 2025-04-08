package com.example.AuthService.exception;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
