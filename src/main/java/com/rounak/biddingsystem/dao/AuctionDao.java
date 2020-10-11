package com.rounak.biddingsystem.dao;

import com.rounak.biddingsystem.entity.Auction;
import com.rounak.biddingsystem.enums.AuctionStatus;
import java.util.List;
import java.util.Optional;

public interface AuctionDao {

  List<Auction> findByStatus(AuctionStatus auctionStatus);

  Optional<Auction> findByItemCodeAndStatus(String itemCode, AuctionStatus auctionStatus);

  Auction save(Auction auction);
}
