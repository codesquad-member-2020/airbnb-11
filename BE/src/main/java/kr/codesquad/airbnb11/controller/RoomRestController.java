package kr.codesquad.airbnb11.controller;

import java.time.LocalDate;
import kr.codesquad.airbnb11.common.error.ApiResult;
import kr.codesquad.airbnb11.controller.request.ReservationRequest;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import kr.codesquad.airbnb11.domain.reservation.Reservation;
import kr.codesquad.airbnb11.domain.reservation.ReservationRepository;
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
  private final ReservationRepository reservationRepository;

  public RoomRestController(RoomDAO roomDAO,
      ReservationRepository reservationRepository) {
    this.roomDAO = roomDAO;
    this.reservationRepository = reservationRepository;
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

    // TODO: 유저정보 jwt를 이용해서 저장하도록 변경
    int userId = 1;

    int daysBetweenCheckInCheckOut = reservationRequest.daysBetweenCheckInCheckOut();
    log.debug("두 날짜간 차이: {}", daysBetweenCheckInCheckOut);

    for (int i = 0; i < daysBetweenCheckInCheckOut; i++) {
      LocalDate accommodationDate = reservationRequest.getCheckIn().plusDays(i);
      log.debug("예약일: {}", accommodationDate);

      Reservation reservation = Reservation.of(accommodationDate, reservationRequest, userId);
      log.debug("DB 저장 전, 예약정보: {}", reservation);

      reservation = reservationRepository.save(reservation);
      log.debug("DB 저장 후, 예약정보: {}", reservation);
    }
    return ApiResult.OK("예약이 정상적으로 완료되었습니다.");
  }
}
