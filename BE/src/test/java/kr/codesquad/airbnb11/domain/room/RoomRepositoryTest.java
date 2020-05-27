package kr.codesquad.airbnb11.domain.room;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void 데이터_생성() {
    Room r1 = Room.builder()
        .title("room1")
        .maxPersonCount(8)
        .build();

    Room r2 = Room.builder()
        .title("room2")
        .maxPersonCount(7)
        .build();

    Room r3 = Room.builder()
        .title("room3")
        .maxPersonCount(6)
        .build();

    Room r4 = Room.builder()
        .title("room4")
        .maxPersonCount(10)
        .build();

    repository.save(r1);
    repository.save(r2);
    repository.save(r3);
    repository.save(r4);
  }

  @Test
  void 조건에_해당하는_방이_없을때() {
    List<Room> responseList = repository.findAllByMaxPersonCountIsGreaterThanEqual(11);
    assertThat(responseList.size()).isEqualTo(0);
  }

  @Test
  void 최소인원_이상_방_검색하기() {
    List<Room> temp = repository.findAllByMaxPersonCountIsGreaterThanEqual(0);
    assertThat(temp.size()).isEqualTo(4);

    int minPersonCount = 8;

    List<Room> responseList = repository.findAllByMaxPersonCountIsGreaterThanEqual(minPersonCount);
    int size = responseList.size();

    assertThat(size).isNotEqualTo(4);
    assertThat(size).isEqualTo(2);
  }
}
