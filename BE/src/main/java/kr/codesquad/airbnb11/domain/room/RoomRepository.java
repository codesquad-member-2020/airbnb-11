package kr.codesquad.airbnb11.domain.room;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {

  List<Room> findAllByMaxPersonCountIsGreaterThanEqual(int minPersonCount);

  List<Room> findAllByDailyPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
