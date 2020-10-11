package com.rounak.biddingsystem.service;

import com.rounak.biddingsystem.dto.AuctionDto;
import java.util.List;

public interface AuctionService {

  List<AuctionDto> getAuctionByStatus(String auctionStatus);

  void placeBid(String itemCode, Double bidAmount, Long userId);

}
