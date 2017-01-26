package com.thousandtwitters.controller.rest.exception;

public class InvalidDAOParameterException extends RuntimeException {
    public InvalidDAOParameterException(String message) {
        super(message);
    }
}
