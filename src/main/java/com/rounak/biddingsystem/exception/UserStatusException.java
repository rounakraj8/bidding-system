package com.rounak.biddingsystem.exception;

public class UserStatusException extends RuntimeException {

  private static final String MSG = "Invalid User Status: ";

  public UserStatusException() {
    super(MSG);
  }

}
