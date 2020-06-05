package kr.codesquad.airbnb11.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import kr.codesquad.airbnb11.common.error.ApiResult;
import kr.codesquad.airbnb11.common.error.exception.UserNotFoundException;
import kr.codesquad.airbnb11.controller.request.CancelReservationRequest;
import kr.codesquad.airbnb11.domain.reservation.Reservation;
import kr.codesquad.airbnb11.domain.user.User;
import kr.codesquad.airbnb11.domain.user.UserRepository;
import kr.codesquad.airbnb11.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Reservation")
@RequestMapping("/reservations")
@RestController
public class ReservationRestController {

  private static final Logger log = LoggerFactory.getLogger(ReservationRestController.class);

  private final ReservationService reservationService;
  private final UserRepository userRepository;

  public ReservationRestController(ReservationService reservationService,
      UserRepository userRepository) {
    this.reservationService = reservationService;
    this.userRepository = userRepository;
  }

  @ApiOperation(value = "", notes = "유저의 앞으로 다가올 예약정보 조회기능")
  @GetMapping("/upcoming")
  public List<Reservation> showUpcomingReservations() {
    // TODO: user 정보 받아서 User에 대한 것만 조회되도록 변경
    User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);
    log.debug("사용자 정보: {}", user);

    List<Reservation> reservations = reservationService.findUsersUpcomingReservation(user);
    log.debug("사용자의 예약 목록: {}", reservations);

    return reservations;
  }

  @ApiOperation(value = "", notes = "유저의 과거 예약정보 조회기능")
  @GetMapping("/past")
  public List<Reservation> showPastReservations() {
    // TODO: user 정보 받아서 User에 대한 것만 조회되도록 변경
    User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);
    log.debug("사용자 정보: {}", user);

    List<Reservation> reservations = reservationService.findUsersPastReservation(user);
    log.debug("사용자의 예약 목록: {}", reservations);

    return reservations;
  }

  @DeleteMapping("/cancel")
  public ApiResult<String> cancelReservation(@RequestBody CancelReservationRequest request) {
    // TODO: user 정보 받아서 User에 대한 것만 조회되도록 변경
    User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);
    log.debug("사용자 정보: {}", user);

    List<Reservation> canceledReservation = reservationService.cancelReservation(user, request);
    log.debug("삭제된 예약 정보: {}", canceledReservation);

    return ApiResult.OK("예약이 정상적으로 취소되었습니다.");
  }
}
