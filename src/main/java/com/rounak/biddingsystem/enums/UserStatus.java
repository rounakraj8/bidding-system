package com.rounak.biddingsystem.enums;

import com.rounak.biddingsystem.exception.UserStatusException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum UserStatus {

  LOGGED_IN("LOGGED_IN"), LOGGED_OUT("LOGGED_OUT");

  private final String status;

  UserStatus(String status) {
    this.status = status;
  }

  public static UserStatus getUserStatus(String status) {
    for (UserStatus auctionStatus : values()) {
      if (Objects.equals(auctionStatus.status, status)) {
        return auctionStatus;
      }
    }
    log.error("Invalid Bid Status: '{}'", status);
    throw new UserStatusException();
  }
}
