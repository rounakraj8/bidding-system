package com.rounak.biddingsystem.dao;

import com.rounak.biddingsystem.entity.Auction;
import com.rounak.biddingsystem.enums.AuctionStatus;
import com.rounak.biddingsystem.repository.AuctionRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@AllArgsConstructor
public class AuctionDaoImpl implements AuctionDao {

  private final AuctionRepository auctionRepository;

  @Override
  public List<Auction> findByStatus(AuctionStatus auctionStatus) {
    return auctionRepository.findByStatus(auctionStatus.name());
  }

  @Override
  public Optional<Auction> findByItemCodeAndStatus(String itemCode, AuctionStatus auctionStatus) {
    return auctionRepository.findByItemCodeAndStatus(itemCode, auctionStatus.name());
  }
}
