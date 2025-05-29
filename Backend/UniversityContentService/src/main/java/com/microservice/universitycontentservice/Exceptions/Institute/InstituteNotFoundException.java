package com.microservice.universitycontentservice.Exceptions.Institute;
public class InstituteNotFoundException extends RuntimeException {
  public InstituteNotFoundException(String message) {
    super(message);
  }
}
