package com.rounak.biddingsystem.service.impl;


import com.rounak.biddingsystem.dao.AuctionDao;
import com.rounak.biddingsystem.dao.BidDao;
import com.rounak.biddingsystem.dto.AuctionDto;
import com.rounak.biddingsystem.entity.Auction;
import com.rounak.biddingsystem.entity.Bid;
import com.rounak.biddingsystem.entity.User;
import com.rounak.biddingsystem.enums.AuctionStatus;
import com.rounak.biddingsystem.enums.BidStatus;
import com.rounak.biddingsystem.exception.AuctionNotFoundException;
import com.rounak.biddingsystem.exception.BidUnacceptableException;
import com.rounak.biddingsystem.mapper.AuctionMapper;
import com.rounak.biddingsystem.service.AuctionService;
import com.rounak.biddingsystem.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

  private final AuctionDao auctionDao;
  private final AuctionMapper auctionMapper;
  private final BidDao bidDao;
  private final UserService userService;


  @Override
  @Transactional
  public List<AuctionDto> getAuctionByStatus(String auctionStatus) {
    List<Auction> auctionEntities = auctionDao
        .findByStatus(AuctionStatus.getAuctionStatus(auctionStatus));
    return auctionMapper.getAuctionDto(auctionEntities);
  }

  @Override
  public void placeBid(String itemCode, Double bidAmount, Long userId) {
    User user = userService.checkIfUserIsLoggedIn(userId);
    BidStatus bidStatus = findAndSaveBid(itemCode, bidAmount, user);
    handleCasesWhenBidIsNotAcceptable(itemCode, bidAmount, bidStatus);
  }

  @Transactional
  protected BidStatus findAndSaveBid(String itemCode, Double bidAmount, User user) {
    Optional<Auction> activeAuction = auctionDao
        .findByItemCodeAndStatus(itemCode, AuctionStatus.RUNNING);
    if (activeAuction.isEmpty()) {
      return BidStatus.AUCTION_NOT_FOUND;
    } else {
      return saveIfAuctionIsRunning(bidAmount, activeAuction.get(), user);
    }
  }

  @NonNull
  private BidStatus saveIfAuctionIsRunning(Double bidAmount, Auction activeAuction, User user) {
    BidStatus bidStatus;
    if (isBidAcceptable(bidAmount, activeAuction)) {
      bidStatus = BidStatus.ACCEPTED;
      saveBid(bidAmount, activeAuction, bidStatus, user);
      updateAuctionWithBidAmount(bidAmount, activeAuction);
    } else {
      bidStatus = BidStatus.REJECTED;
      saveBid(bidAmount, activeAuction, bidStatus, user);
    }
    return bidStatus;
  }

  private void updateAuctionWithBidAmount(Double bidAmount, Auction activeAuction) {
    activeAuction.setMaxBidAmount(bidAmount);
    auctionDao.save(activeAuction);
  }

  private boolean isBidAcceptable(Double bidAmount, Auction auction) {
    return auction.getMinBasePrice() < bidAmount
        && auction.getMaxBidAmount() + auction.getStepPrice() <= bidAmount;
  }

  private void saveBid(Double bidAmount, Auction activeAuction, BidStatus bidStatus, User user) {
    Bid newBid = new Bid();
    newBid.setAuctionId(activeAuction.getId());
    newBid.setUserId(user.getId());
    newBid.setBidAmount(bidAmount);
    newBid.setStatus(bidStatus.name());
    bidDao.save(newBid);
  }

  private void handleCasesWhenBidIsNotAcceptable(String itemCode, Double bidAmount,
      BidStatus bidStatus) {
    switch (bidStatus) {
      case AUCTION_NOT_FOUND:
        throw new AuctionNotFoundException(itemCode, bidAmount);
      case REJECTED:
        throw new BidUnacceptableException(itemCode, bidAmount);
    }
  }
}
