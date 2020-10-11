package com.rounak.biddingsystem.mapper;

import com.rounak.biddingsystem.dto.AuctionDto;
import com.rounak.biddingsystem.entity.Auction;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {

  public List<AuctionDto> getAuctionDto(List<Auction> entityList) {
    return entityList.stream()
        .map(entity -> new AuctionDto(entity.getItemCode(),
            entity.getStepPrice(),
            entity.getMaxBidAmount()))
        .collect(Collectors.toList());
  }
}
