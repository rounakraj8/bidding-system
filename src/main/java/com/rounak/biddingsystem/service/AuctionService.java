package com.rounak.biddingsystem.service;

import com.rounak.biddingsystem.dto.Auction;
import java.util.List;

public interface AuctionService {

  List<Auction> getAuctionByStatus(String status);

  void placeBid(Long itemCode, double bidAmount);

}
