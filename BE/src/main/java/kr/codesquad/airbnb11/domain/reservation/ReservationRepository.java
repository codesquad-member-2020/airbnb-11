package kr.codesquad.airbnb11.domain.reservation;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {

  List<Reservation> findAllByGuestIdAndReservationDateGreaterThanEqualOrderByReservationDate(
      Integer guestId, LocalDate date);

  List<Reservation> findAllByGuestIdAndReservationDateLessThanOrderByReservationDate(
      Integer guestId, LocalDate date);

  List<Reservation> deleteByGuestIdAndRoomIdAndReservationDateBetween(
      Integer guestId, Integer roomId, LocalDate startDate, LocalDate endDate);
}
