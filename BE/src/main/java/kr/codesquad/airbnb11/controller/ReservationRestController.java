package kr.codesquad.airbnb11.controller;

import io.swagger.annotations.Api;
import kr.codesquad.airbnb11.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Reservation")
@RequestMapping("/reservations")
@RestController
public class ReservationRestController {

  private static final Logger log = LoggerFactory.getLogger(ReservationRestController.class);

  private final ReservationService reservationService;

  public ReservationRestController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }
}
