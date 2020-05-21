package kr.codesquad.airbnb11.controller;

import java.util.Collections;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.RoomResponse.Builder;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import org.joda.money.BigMoney;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rooms")
@RestController
public class RoomRestController {

  private static final Logger log = LoggerFactory.getLogger(RoomRestController.class);

  @GetMapping("/search")
  public SearchResponse searchRooms(@RequestBody(required = false) SearchRequest searchRequest) {
    log.debug("searchRequest: {}", searchRequest);
    SearchResponse searchResponse = new SearchResponse();
    searchResponse.setRoomsCount(1);
    searchResponse.setRooms(Collections.singletonList(Builder.aRoomResponse()
        .id(1)
        .maxPersonCount(2)
        .mainImage("https://i.imgur.com/dhisgFy.png")
        .title("Charming House")
        .dailyPrice(BigMoney.parse("KRW 239816").getAmountMajor())
        .country("프랑스")
        .build()
    ));
    return searchResponse;
  }

}
