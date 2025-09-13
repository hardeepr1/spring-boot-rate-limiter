package com.hsingh.flowcap.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(final String reason){
        super(reason);
    }
}
