package com.univault.ims.exception.service;
public class InstituteServiceException extends RuntimeException {
    public InstituteServiceException(String message) {
        super(message);
    }
    public InstituteServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
