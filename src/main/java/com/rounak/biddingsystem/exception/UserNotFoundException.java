package com.rounak.biddingsystem.exception;

public class UserNotFoundException extends RuntimeException {

  private static final String MSG = "User not found with userId: %s";

  public UserNotFoundException(Long username) {
    super(String.format(MSG, username));
  }

}
