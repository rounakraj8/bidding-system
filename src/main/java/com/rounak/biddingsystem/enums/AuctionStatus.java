package com.rounak.biddingsystem.enums;

import com.rounak.biddingsystem.exception.InvalidAuctionStatusException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum AuctionStatus {

  RUNNING("RUNNING"), OVER("OVER");

  private final String status;

  AuctionStatus(String status) {
    this.status = status;
  }

  public static AuctionStatus getAuctionStatus(String status) {
    for (AuctionStatus auctionStatus : values()) {
      if (Objects.equals(auctionStatus.status, status)) {
        return auctionStatus;
      }
    }
    log.error("Invalid Auction Status: '{}'", status);
    throw new InvalidAuctionStatusException(status);
  }
}
