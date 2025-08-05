package com.univault.ims.exception.service;

public class SemesterServiceException extends RuntimeException {
    public SemesterServiceException(String message) {
        super(message);
    }

    public SemesterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
