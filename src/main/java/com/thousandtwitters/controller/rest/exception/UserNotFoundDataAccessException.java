package com.thousandtwitters.controller.rest.exception;

import org.springframework.dao.DataAccessException;

public class UserNotFoundDataAccessException extends DataAccessException {

    private DataAccessException nestedException;

    public UserNotFoundDataAccessException(String message, DataAccessException nestedException) {
        super(message);
        this.nestedException = nestedException;
    }

    public DataAccessException getNestedException() {
        return nestedException;
    }
}

