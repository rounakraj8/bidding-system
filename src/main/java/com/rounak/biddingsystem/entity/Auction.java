package com.rounak.biddingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Auction {

  @Id
  private Long id;
  private String itemCode;
  private String status;
  private Double minBasePrice;
  private Double stepPrice;
  private Double maxBidAmount;

}
