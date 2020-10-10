package com.rounak.biddingsystem.service;

import com.rounak.biddingsystem.dto.Auction;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {

  @Override
  public List<Auction> getAuctionByStatus(String status) {
    return new ArrayList<>();
  }

  @Override
  public void placeBid(Long itemCode, double bidAmount) {

  }
}
