package com.microservice.universitycontentservice.Exceptions.Branch;
public class BranchNotFoundException extends RuntimeException {
  public BranchNotFoundException(String message) {
    super(message);
  }
}
