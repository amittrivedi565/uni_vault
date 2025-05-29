package com.microservice.universitycontentservice.Exceptions.Branch;

public class BranchAlreadyExistsException extends RuntimeException {
  public BranchAlreadyExistsException(String message) {
    super(message);
  }
}
