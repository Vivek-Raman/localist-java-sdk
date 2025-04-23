package com.localist.sdk;

import org.springframework.http.HttpStatus;

public class LocalistAPIException extends RuntimeException {
  private final HttpStatus status;

  public LocalistAPIException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public LocalistAPIException(String message, HttpStatus status, Throwable cause) {
    super(message, cause);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}