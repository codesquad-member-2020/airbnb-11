package kr.codesquad.airbnb11.service;

import java.time.LocalDate;
import kr.codesquad.airbnb11.controller.request.ReservationRequest;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import kr.codesquad.airbnb11.domain.reservation.Reservation;
import kr.codesquad.airbnb11.domain.reservation.ReservationRepository;
import kr.codesquad.airbnb11.domain.room.RoomDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {

  private static final Logger log = LoggerFactory.getLogger(RoomService.class);

  private final RoomDAO roomDAO;
  private final ReservationRepository reservationRepository;

  public RoomService(RoomDAO roomDAO, ReservationRepository reservationRepository) {
    this.roomDAO = roomDAO;
    this.reservationRepository = reservationRepository;
  }

  public SearchResponse findAvailableRooms(SearchRequest searchRequest) {
    SearchResponse searchResponse = new SearchResponse(
        roomDAO.selectRoomsWithSearchParams(searchRequest));
    log.debug("검색 결과: {}", searchResponse);
    return searchResponse;
  }

  @Transactional
  public void reserveRoom(ReservationRequest reservationRequest, int userId) {
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
  }
}
