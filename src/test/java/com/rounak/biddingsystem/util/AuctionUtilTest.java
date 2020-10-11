package com.rounak.biddingsystem.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rounak.biddingsystem.entity.Auction;
import com.rounak.biddingsystem.entity.Bid;
import com.rounak.biddingsystem.enums.AuctionStatus;
import com.rounak.biddingsystem.enums.BidStatus;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class AuctionUtilTest {

  @Test
  void getBidAmount() {
    Auction entity = getAuctionEntity(1L, "ITEM001", 1100.00, AuctionStatus.RUNNING);
    entity.setBidSet(getBids());
    assertEquals(1300.00, AuctionUtil.getBidAmount(entity));

  }

  private Auction getAuctionEntity(Long auctionId, String itemCode, Double minBasePrice,
      AuctionStatus auctionStatus) {
    Auction entity = new Auction();
    entity.setId(auctionId);
    entity.setItemCode(itemCode);
    entity.setMinBasePrice(minBasePrice);
    entity.setStatus(auctionStatus.name());
    return entity;
  }


  private List<Bid> getBids() {
    Bid bid1 = new Bid(1L, 1L, 1L, 1200.00, BidStatus.ACCEPTED.name());
    Bid bid2 = new Bid(2L, 1L, 1L, 1300.00, BidStatus.ACCEPTED.name());
    Bid bid3 = new Bid(3L, 1L, 1L, 1100.00, BidStatus.ACCEPTED.name());
    Bid bid4 = new Bid(4L, 1L, 1L, 900.00, BidStatus.REJECTED.name());
    Bid bid5 = new Bid(5L, 1L, 1L, 1300.00, BidStatus.REJECTED.name());
    return Arrays.asList(bid1, bid2, bid3, bid4, bid5);
  }
}