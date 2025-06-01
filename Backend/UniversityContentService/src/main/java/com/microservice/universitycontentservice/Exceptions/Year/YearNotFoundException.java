package com.microservice.universitycontentservice.Exceptions.Year;
public class YearNotFoundException extends RuntimeException {
  public YearNotFoundException(String message) {
    super(message);
  }
}
