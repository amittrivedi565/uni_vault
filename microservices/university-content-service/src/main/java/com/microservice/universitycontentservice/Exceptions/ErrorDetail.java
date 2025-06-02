package com.microservice.universitycontentservice.Exceptions;

import java.util.Date;

public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetail(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
