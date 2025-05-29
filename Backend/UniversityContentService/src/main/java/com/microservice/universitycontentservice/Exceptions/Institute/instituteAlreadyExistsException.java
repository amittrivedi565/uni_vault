package com.microservice.universitycontentservice.Exceptions.Institute;

public class instituteAlreadyExistsException extends RuntimeException {
  public instituteAlreadyExistsException(String message) {
    super(message);
  }
}
