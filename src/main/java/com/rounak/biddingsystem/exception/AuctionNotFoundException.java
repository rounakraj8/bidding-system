package com.rounak.biddingsystem.exception;

public class AuctionNotFoundException extends RuntimeException {

  private static final String MSG = "No running auction found for : %s, bid amount %s";

  public AuctionNotFoundException(String itemCode, Double bidAmount) {
    super(String.format(MSG, itemCode, bidAmount));
  }

}
