package com.rounak.biddingsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.rounak.biddingsystem.dao.AuctionDao;
import com.rounak.biddingsystem.dao.BidDao;
import com.rounak.biddingsystem.dto.AuctionDto;
import com.rounak.biddingsystem.entity.Auction;
import com.rounak.biddingsystem.entity.User;
import com.rounak.biddingsystem.enums.AuctionStatus;
import com.rounak.biddingsystem.enums.UserStatus;
import com.rounak.biddingsystem.exception.AuctionNotFoundException;
import com.rounak.biddingsystem.exception.BidUnacceptableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AuctionServiceImplTest {

  @Autowired
  private AuctionService auctionService;
  @MockBean
  private AuctionDao auctionDao;
  @MockBean
  private BidDao bidDao;
  @MockBean
  private UserService userService;

  @Test
  void getAuctionByStatus_noRunningAuction() {
    when(auctionDao.findByStatus(AuctionStatus.RUNNING)).thenReturn(new ArrayList<>());
    List<AuctionDto> auctionDtoList = auctionService
        .getAuctionByStatus(AuctionStatus.RUNNING.name());
    assertThat(auctionDtoList.size()).isEqualTo(0);
  }

  @Test
  void getAuctionByStatus_fewRunningAuction() {
    when(auctionDao.findByStatus(AuctionStatus.RUNNING)).thenReturn(getRunningAuction());
    List<AuctionDto> auctionDtos = auctionService.getAuctionByStatus(AuctionStatus.RUNNING.name());
    assertThat(auctionDtos.size()).isEqualTo(8);
  }

  @Test
  void placeBid_noActiveAuction() {
    when(auctionDao.findByItemCodeAndStatus("ITEM-001", AuctionStatus.RUNNING))
        .thenReturn(Optional.empty());
    when(userService.checkIfUserIsLoggedIn(1L))
        .thenReturn(new User(1L, "User1", UserStatus.LOGGED_IN.name()));
    assertThrows(AuctionNotFoundException.class,
        () -> auctionService.placeBid("ITEM-001", 1200.00, 1L));
  }

  @Test
  void placeBid_bidUnacceptable() {
    when(auctionDao.findByItemCodeAndStatus("ITEM001", AuctionStatus.RUNNING))
        .thenReturn(
            Optional.of(new Auction(2L, "ITEM001", AuctionStatus.RUNNING.name(), 1500.00, 50.00,
                1600.00)));
    when(userService.checkIfUserIsLoggedIn(1L))
        .thenReturn(new User(1L, "User1", UserStatus.LOGGED_IN.name()));
    assertThrows(BidUnacceptableException.class,
        () -> auctionService.placeBid("ITEM001", 1200.00, 1L));
  }

  @Test
  void placeBid_bidAcceptable() {
    when(auctionDao.findByItemCodeAndStatus("ITEM001", AuctionStatus.RUNNING))
        .thenReturn(
            Optional.of(new Auction(2L, "ITEM001", AuctionStatus.RUNNING.name(), 1500.00, 50.00,
                1500.00)));
    when(userService.checkIfUserIsLoggedIn(1L))
        .thenReturn(new User(1L, "User1", UserStatus.LOGGED_IN.name()));
    auctionService.placeBid("ITEM001", 1700.00, 1L);
  }


  private List<Auction> getRunningAuction() {
    Auction auction1 = new Auction(1L, "ITEM001", AuctionStatus.RUNNING.name(), 500.00, 50.00,
        500.00);
    Auction auction2 = new Auction(2L, "ITEM002", AuctionStatus.RUNNING.name(), 1500.00, 50.00,
        1500.00);
    Auction auction3 = new Auction(3L, "ITEM003", AuctionStatus.RUNNING.name(), 2500.00, 50.00,
        2500.00);
    Auction auction4 = new Auction(4L, "ITEM004", AuctionStatus.RUNNING.name(), 3500.00, 50.00,
        3500.00);
    Auction auction5 = new Auction(5L, "ITEM005", AuctionStatus.RUNNING.name(), 4500.00, 50.00,
        4500.00);
    Auction auction6 = new Auction(6L, "ITEM006", AuctionStatus.RUNNING.name(), 5500.00, 50.00,
        5500.00);
    Auction auction7 = new Auction(7L, "ITEM007", AuctionStatus.RUNNING.name(), 6500.00, 50.00,
        6500.00);
    Auction auction8 = new Auction(8L, "ITEM008", AuctionStatus.RUNNING.name(), 7500.00, 50.00,
        7500.00);

    return Arrays
        .asList(auction1, auction2, auction3, auction4, auction5, auction6, auction7, auction8);
  }

}