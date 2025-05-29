package com.service.src.Exceptions.Branch;

public class branchNotFoundException extends RuntimeException {
  public branchNotFoundException(String message) {
    super(message);
  }
}
