package com.microservice.universitycontentservice.Exceptions.Year;
public class YearServiceException extends RuntimeException {
    public YearServiceException(String message) {
        super(message);
    }
}
