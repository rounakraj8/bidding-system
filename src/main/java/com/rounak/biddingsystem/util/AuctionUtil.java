package com.rounak.biddingsystem.util;

import com.rounak.biddingsystem.entity.Auction;
import com.rounak.biddingsystem.entity.Bid;
import java.util.Comparator;

public class AuctionUtil {

  public static Double getBidAmount(Auction entity) {
    return (entity.getBidSet().stream().max(Comparator.comparing(Bid::getBidAmount))
        .orElse(new Bid(0L, 0L, 0L, 0.00, ""))).getBidAmount();
  }
}
