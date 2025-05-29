package com.microservice.universitycontentservice.Exceptions.Institute;

public class InstituteAlreadyExistsException extends RuntimeException {
  public InstituteAlreadyExistsException(String message) {
    super(message);
  }
}
