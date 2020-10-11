package com.rounak.biddingsystem.repository;

import com.rounak.biddingsystem.entity.Auction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, Long> {

  List<Auction> findByStatus(String auctionStatus);

  Optional<Auction> findByItemCodeAndStatus(String itemCode, String name);
}
