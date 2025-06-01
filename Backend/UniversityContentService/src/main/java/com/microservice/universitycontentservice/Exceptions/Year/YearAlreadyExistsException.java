package com.microservice.universitycontentservice.Exceptions.Year;

public class YearAlreadyExistsException extends RuntimeException {
  public YearAlreadyExistsException(String message) {
    super(message);
  }
}
