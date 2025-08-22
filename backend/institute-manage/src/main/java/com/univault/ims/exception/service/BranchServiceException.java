package com.univault.ims.exception.service;
public class BranchServiceException extends RuntimeException {
    public BranchServiceException(String message) {
        super(message);
    }
    public BranchServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
