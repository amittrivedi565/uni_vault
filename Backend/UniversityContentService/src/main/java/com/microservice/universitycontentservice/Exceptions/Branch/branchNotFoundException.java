package com.microservice.universitycontentservice.Exceptions.Branch;
public class branchNotFoundException extends RuntimeException {
  public branchNotFoundException(String message) {
    super(message);
  }
}
