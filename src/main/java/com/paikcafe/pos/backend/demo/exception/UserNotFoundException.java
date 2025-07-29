package com.paikcafe.pos.backend.demo.exception;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super("User not found", "USER_NOT_FOUND");
    }
}
