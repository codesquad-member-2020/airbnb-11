package kr.codesquad.airbnb11.service;

import java.time.LocalDate;
import java.util.List;
import kr.codesquad.airbnb11.controller.request.CancelReservationRequest;
import kr.codesquad.airbnb11.domain.reservation.Reservation;
import kr.codesquad.airbnb11.domain.reservation.ReservationRepository;
import kr.codesquad.airbnb11.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

  private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public List<Reservation> findUsersUpcomingReservation(User user) {
    log.debug("사용자의 Id: {}", user.getId());

    return reservationRepository
        .findAllByGuestIdAndReservationDateGreaterThanEqualOrderByReservationDate(user.getId(),
            LocalDate.now());
  }

  public List<Reservation> findUsersPastReservation(User user) {
    log.debug("사용자의 Id: {}", user.getId());

    return reservationRepository
        .findAllByGuestIdAndReservationDateLessThanOrderByReservationDate(user.getId(),
            LocalDate.now());
  }

  @Transactional
  public List<Reservation> cancelReservation(User user, CancelReservationRequest request) {
    log.debug("사용자의 Id: {}, 예약 정보: {}", user.getId(), request);

    return reservationRepository.deleteByGuestIdAndRoomIdAndReservationDateBetween(
        user.getId(), request.getRoomId(), request.getStartDate(), request.getEndDate()
    );
  }
}
