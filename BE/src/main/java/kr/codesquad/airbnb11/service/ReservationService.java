package kr.codesquad.airbnb11.service;

import kr.codesquad.airbnb11.domain.reservation.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }
}
