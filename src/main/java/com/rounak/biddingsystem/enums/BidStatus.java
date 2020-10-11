package com.rounak.biddingsystem.enums;

import com.rounak.biddingsystem.exception.InvalidBidStatusException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum BidStatus {

  ACCEPTED("ACCEPTED"), REJECTED("REJECTED"), AUCTION_NOT_FOUND("AUCTION_NOT_FOUND");

  private final String status;

  BidStatus(String status) {
    this.status = status;
  }

  public static BidStatus getAuctionStatus(String status) {
    for (BidStatus auctionStatus : values()) {
      if (Objects.equals(auctionStatus.status, status)) {
        return auctionStatus;
      }
    }
    log.error("Invalid Bid Status: '{}'", status);
    throw new InvalidBidStatusException();
  }
}
