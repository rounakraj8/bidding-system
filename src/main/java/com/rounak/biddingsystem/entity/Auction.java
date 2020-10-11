package com.rounak.biddingsystem.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @OneToMany(mappedBy = "auctionId", fetch = FetchType.EAGER)
  private Collection<Bid> bidSet;
}
