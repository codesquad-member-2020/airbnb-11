package kr.codesquad.airbnb11.domain.room;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {

  @Transactional(readOnly = true)
  List<Room> findAllByMaxPersonCountIsLessThanEqual(int maxPersonCount);
}
