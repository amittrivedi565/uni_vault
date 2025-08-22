package com.univault.ims.exception.service;

public class UnitServiceException extends RuntimeException {
    public UnitServiceException(String message) {
        super(message);
    }

    public UnitServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
