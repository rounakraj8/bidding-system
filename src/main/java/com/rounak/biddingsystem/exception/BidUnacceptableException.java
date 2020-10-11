package com.rounak.biddingsystem.exception;

public class BidUnacceptableException extends RuntimeException {

  private static final String MSG = "Bid is unacceptable for itemCode %s with bidAmount %s !";

  public BidUnacceptableException(String itemCode, Double bidAmount) {
    super(String.format(MSG, itemCode, bidAmount));
  }

}
