package com.paikcafe.pos.backend.demo.exception;

public class InvalidCredentialsException extends ApiException {
    public InvalidCredentialsException() {
        super("Invalid credentials", "INVALID_CREDENTIALS");
    }
}
