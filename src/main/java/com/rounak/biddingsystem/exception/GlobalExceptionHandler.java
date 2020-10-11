package com.rounak.biddingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidAuctionStatusException.class)
  public ResponseEntity<Object> handle(
      InvalidAuctionStatusException invalidAuctionStatusException) {
    return new ResponseEntity<>(invalidAuctionStatusException.getMessage(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuctionNotFoundException.class)
  public ResponseEntity<Object> handle(
      AuctionNotFoundException auctionNotFoundException) {
    return new ResponseEntity<>(auctionNotFoundException.getMessage(),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BidUnacceptableException.class)
  public ResponseEntity<Object> handle(
      BidUnacceptableException bidUnacceptableException) {
    return new ResponseEntity<>(bidUnacceptableException.getMessage(),
        HttpStatus.NOT_ACCEPTABLE);
  }


}
