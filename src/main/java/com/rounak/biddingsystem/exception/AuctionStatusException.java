package com.rounak.biddingsystem.exception;

public class AuctionStatusException extends RuntimeException {

  private static final String MSG = "Invalid Auction Status: ";

  public AuctionStatusException(String status) {
    super(MSG + status);
  }

}
