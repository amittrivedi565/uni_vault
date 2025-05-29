package com.service.src.Exceptions.Branch;

public class branchAlreadyExistsException extends RuntimeException {
  public branchAlreadyExistsException(String message) {
    super(message);
  }
}
