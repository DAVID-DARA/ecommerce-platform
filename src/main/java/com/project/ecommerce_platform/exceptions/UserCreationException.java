package com.project.ecommerce_platform.exceptions;

public class UserCreationException extends RuntimeException {

    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
