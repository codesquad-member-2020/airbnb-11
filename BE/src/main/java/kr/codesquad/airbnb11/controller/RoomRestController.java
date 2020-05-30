package kr.codesquad.airbnb11.controller;

import kr.codesquad.airbnb11.common.error.ApiResult;
import kr.codesquad.airbnb11.controller.request.ReservationRequest;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import kr.codesquad.airbnb11.service.RoomService;
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

  private final RoomService roomService;

  public RoomRestController(RoomService roomService) {
    this.roomService = roomService;
  }

  @GetMapping("/search")
  public SearchResponse searchRooms(@ModelAttribute SearchRequest searchRequest) {
    log.debug("searchRequest: {}", searchRequest);

    SearchResponse searchResponse = roomService.findAvailableRooms(searchRequest);
    log.debug("searchResponse: {}", searchResponse);
    return searchResponse;
  }

  @PostMapping("/reservation")
  public ApiResult<String> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
    log.debug("예약 요청 객체: {}", reservationRequest);

    // TODO: 유저정보 jwt를 이용해서 저장하도록 변경
    int userId = 1;

    roomService.reserveRoom(reservationRequest, userId);
    return ApiResult.OK("예약이 정상적으로 완료되었습니다.");
  }
}
