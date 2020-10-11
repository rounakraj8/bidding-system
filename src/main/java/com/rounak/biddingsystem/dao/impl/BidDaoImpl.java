package com.rounak.biddingsystem.dao.impl;

import com.rounak.biddingsystem.dao.BidDao;
import com.rounak.biddingsystem.entity.Bid;
import com.rounak.biddingsystem.repository.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BidDaoImpl implements BidDao {

  private final BidRepository bidRepository;

  @Override
  public Bid save(Bid bid) {
    return bidRepository.save(bid);
  }
}
