package com.rounak.biddingsystem.controller;

import static com.rounak.biddingsystem.constants.BiddingSystemConstants.AUCTION_BASE_URI;
import static com.rounak.biddingsystem.constants.BiddingSystemConstants.AUCTION_BID_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.rounak.biddingsystem.dto.AuctionDto;
import com.rounak.biddingsystem.service.AuctionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for Auction
 *
 * @author rounakr
 */

@RestController
@RequestMapping(path = AUCTION_BASE_URI, produces = APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class AuctionController {

  private final AuctionService auctionService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AuctionDto> getAuctionByStatus(@RequestParam("status") String auctionStatus) {
    log.info("Request received for getting all auction with status: {}", auctionStatus);
    return auctionService.getAuctionByStatus(auctionStatus);
  }

  @PostMapping(value = AUCTION_BID_URI)
  @ResponseStatus(HttpStatus.CREATED)
  public void placeBid(@PathVariable String itemCode, @RequestBody Double bidAmount,
      @RequestHeader Long userId) {
    log.info("Request received for placing bid for item code {}, bid amount is {}", itemCode,
        bidAmount);
    auctionService.placeBid(itemCode, bidAmount, userId);
  }
}
