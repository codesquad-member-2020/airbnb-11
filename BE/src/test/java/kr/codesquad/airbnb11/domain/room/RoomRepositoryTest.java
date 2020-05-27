package kr.codesquad.airbnb11.domain.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
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
  void 데이터_초기화() {
    Room r1 = Room.builder()
        .title("room1")
        .maxPersonCount(8)
        .dailyPrice(new BigDecimal("43"))
        .build();

    Room r2 = Room.builder()
        .title("room2")
        .maxPersonCount(7)
        .dailyPrice(new BigDecimal("21"))
        .build();

    Room r3 = Room.builder()
        .title("room3")
        .maxPersonCount(6)
        .dailyPrice(new BigDecimal("15"))
        .build();

    Room r4 = Room.builder()
        .title("room4")
        .maxPersonCount(10)
        .dailyPrice(new BigDecimal("10"))
        .build();

    repository.save(r1);
    repository.save(r2);
    repository.save(r3);
    repository.save(r4);
  }

  @Test
  void 조건에_해당하는_방이_없을때() {
    List<Room> responseList = repository.findAllByMaxPersonCountIsGreaterThanEqual(12);
    assertThat(responseList.size()).isEqualTo(0);
  }
  
  @Test
  void 최소인원_이상_방_검색하기() {
    int minPersonCount = 8;

    List<Room> responseList = repository.findAllByMaxPersonCountIsGreaterThanEqual(minPersonCount);
    int size = responseList.size();

    assertThat(size).isNotEqualTo(4);
    assertThat(size).isEqualTo(2);
  }

  @Test
  void 요금_범위검색_테스트() {
    List<Room> responseList = repository
        .findAllByDailyPriceBetween(BigDecimal.ZERO, new BigDecimal("15"));
    logger.debug("검색결과: {}", responseList);
    assertThat(responseList.size()).isEqualTo(2);
  }

  @Test
  void 요금범위가_null_일때() {
    // null이 들어가면 안되므로, minPrice는 0, maxPrice는 100만달러정도로 하면 될 것 같습니다.
    // 둘 다 null이면 다른 query를 타는 것도 좋을 듯 합니다.
    assertThatThrownBy(() -> {
      List<Room> responseList = repository.findAllByDailyPriceBetween(BigDecimal.ZERO, null);
    }).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Value of parameter with name dailyPrice must not be null!");
  }
}
