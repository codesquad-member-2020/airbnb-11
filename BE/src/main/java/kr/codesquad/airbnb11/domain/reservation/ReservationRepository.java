package kr.codesquad.airbnb11.domain.reservation;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {

}
