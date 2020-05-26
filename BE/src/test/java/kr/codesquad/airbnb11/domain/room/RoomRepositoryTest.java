package kr.codesquad.airbnb11.domain.room;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
class RoomRepositoryTest {

  private static final Logger logger = LoggerFactory.getLogger(RoomRepositoryTest.class);

  @Autowired
  RoomRepository repository;

  @DisplayName("조건에 해당하는 방이 없을 때")
  @Test
  void search_none() {
    List<Room> responseList = repository.findAllByMaxPersonCountIsLessThanEqual(8);
    assertThat(responseList.size()).isEqualTo(0);
  }

  @DisplayName("8명 이하인 방 검색하기")
  @Test
  void search_under_8() {
    Room r1 = Room.Builder.of()
        .title("room1")
        .maxPersonCount(8)
        .build();

    Room r2 = Room.Builder.of()
        .title("room2")
        .maxPersonCount(7)
        .build();

    Room r3 = Room.Builder.of()
        .title("room3")
        .maxPersonCount(6)
        .build();

    Room r4 = Room.Builder.of()
        .title("room4")
        .maxPersonCount(10)
        .build();

    repository.save(r1);
    repository.save(r2);
    repository.save(r3);
    repository.save(r4);

    List<Room> responseList = repository.findAllByMaxPersonCountIsLessThanEqual(8);
    assertThat(responseList.size()).isNotEqualTo(4);
    assertThat(responseList.size()).isEqualTo(3);
  }
}
