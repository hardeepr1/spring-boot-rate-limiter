package com.hsingh.flowcap.exception;

import org.springframework.http.HttpStatus;

public class InvalidLoginCredentialsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Invalid login credentials provided";

    public InvalidLoginCredentialsException() {
        super(DEFAULT_MESSAGE);
    }
}
