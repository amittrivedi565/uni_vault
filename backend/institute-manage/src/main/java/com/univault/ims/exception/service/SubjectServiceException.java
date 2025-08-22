package com.univault.ims.exception.service;

public class SubjectServiceException extends RuntimeException {
    public SubjectServiceException(String message) {
        super(message);
    }

    public SubjectServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
