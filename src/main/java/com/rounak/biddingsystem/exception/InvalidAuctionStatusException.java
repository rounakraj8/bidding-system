package com.rounak.biddingsystem.exception;

public class InvalidAuctionStatusException extends RuntimeException {

  private static final String MSG = "Invalid Auction Status: ";

  public InvalidAuctionStatusException(String status) {
    super(MSG + status);
  }

}
