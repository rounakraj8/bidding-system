package com.rounak.biddingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuctionDto {

  private String itemCode;
  private Double stepPrice;
  private Double highestBidAmount;

}
