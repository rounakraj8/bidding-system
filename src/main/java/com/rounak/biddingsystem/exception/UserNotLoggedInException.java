package com.rounak.biddingsystem.exception;

public class UserNotLoggedInException extends
    RuntimeException {

  private static final String MSG = "User is not logged in!";

  public UserNotLoggedInException() {
    super(MSG);
  }
}
