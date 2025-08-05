package com.univault.ims.exception.service;

public class QuestionPaperServiceException extends RuntimeException {
  public QuestionPaperServiceException(String message) {
    super(message);
  }

  public QuestionPaperServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
