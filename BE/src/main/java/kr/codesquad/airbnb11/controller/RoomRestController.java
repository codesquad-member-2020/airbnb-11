package kr.codesquad.airbnb11.controller;

import kr.codesquad.airbnb11.common.error.ApiResult;
import kr.codesquad.airbnb11.controller.request.ReservationRequest;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import kr.codesquad.airbnb11.domain.room.RoomDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rooms")
@RestController
public class RoomRestController {

  private static final Logger log = LoggerFactory.getLogger(RoomRestController.class);

  private final RoomDAO roomDAO;

  public RoomRestController(RoomDAO roomDAO) {
    this.roomDAO = roomDAO;
  }

  @GetMapping("/search")
  public SearchResponse searchRooms(@ModelAttribute SearchRequest searchRequest) {
    log.debug("searchRequest: {}", searchRequest);

    SearchResponse searchResponse = new SearchResponse(
        roomDAO.selectRoomsWithSearchParams(searchRequest));
    log.debug("searchResponse: {}", searchResponse);
    return searchResponse;
  }

  @PostMapping("/reservation")
  public ApiResult<String> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
    log.debug("예약 요청 객체: {}", reservationRequest);

    throw new RuntimeException("미구현");
  }
}
