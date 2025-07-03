package com.Exceptions;

import java.util.Date;

public class ErrorDetail {
    private final Date timestamp;
    private final String message;
    private final String details;

    public ErrorDetail(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
