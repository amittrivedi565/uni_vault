package com.microservice.universitycontentservice.Exceptions.Branch;

public class branchAlreadyExistsException extends RuntimeException {
  public branchAlreadyExistsException(String message) {
    super(message);
  }
}
