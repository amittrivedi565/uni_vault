package com.microservice.universitycontentservice.Exceptions.Institute;
public class instituteNotFoundException extends RuntimeException {
  public instituteNotFoundException(String message) {
    super(message);
  }
}
