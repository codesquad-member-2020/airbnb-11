package kr.codesquad.airbnb11.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.codesquad.airbnb11.common.error.ApiResult;
import kr.codesquad.airbnb11.controller.request.ReservationRequest;
import kr.codesquad.airbnb11.controller.request.RoomDetailRequest;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.RoomDetailResponse;
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

@Api(tags = "Room")
@RequestMapping("/rooms")
@RestController
public class RoomRestController {

  private static final Logger log = LoggerFactory.getLogger(RoomRestController.class);

  private final RoomService roomService;

  public RoomRestController(RoomService roomService) {
    this.roomService = roomService;
  }

  @ApiOperation(value = "", notes = "검색 기능")
  @GetMapping("/search")
  public SearchResponse searchRooms(@ModelAttribute SearchRequest searchRequest) {
    log.debug("searchRequest: {}", searchRequest);

    SearchResponse searchResponse = roomService.findAvailableRooms(searchRequest);
    log.debug("searchResponse: {}", searchResponse);
    return searchResponse;
  }

  @ApiOperation(value = "", notes = "예약 기능")
  @PostMapping("/reservation")
  public ApiResult<String> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
    log.debug("예약 요청 객체: {}", reservationRequest);

    // TODO: 유저정보 jwt를 이용해서 저장하도록 변경
    int userId = 1;

    roomService.reserveRoom(reservationRequest, userId);
    return ApiResult.OK("예약이 정상적으로 완료되었습니다.");
  }

  @ApiOperation(value = "", notes = "상세 정보 조회 기능")
  @GetMapping("/detail")
  public RoomDetailResponse findRoomDetail(@ModelAttribute RoomDetailRequest roomDetailRequest) {
    log.debug("roomDetailRequest: {}", roomDetailRequest);

    RoomDetailResponse roomDetailResponse = roomService.findRoomDetailByRoomId(roomDetailRequest);
    log.debug("방 상세정보 응답객체: {}", roomDetailResponse);
    return roomDetailResponse;
  }
}
