package kr.codesquad.airbnb11.domain.review;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import kr.codesquad.airbnb11.domain.room.Room;
import kr.codesquad.airbnb11.domain.room.RoomDTO;
import kr.codesquad.airbnb11.domain.room.RoomRepository;
import kr.codesquad.airbnb11.domain.user.User;
import kr.codesquad.airbnb11.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
class ReviewRepositoryTest {

  private static final Logger log = LoggerFactory.getLogger(ReviewRepositoryTest.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoomRepository roomRepository;

  @Autowired
  ReviewRepository reviewRepository;

  Room room1;
  RoomDTO roomDTO1;

  @BeforeEach
  void setUp() {
    Room r1 = Room.builder()
        .title("room1")
        .maxPersonCount(8)
        .dailyPrice(new BigDecimal("43"))
        .build();

    roomRepository.save(r1);

    User u1 = User.builder()
        .email("tes1t@gmail.com")
        .nickname("test1")
        .build();

    userRepository.save(u1);

    Review v1 = new Review(1, "good1", r1.getId(), u1.getId());
    Review v2 = new Review(2, "good2", r1.getId(), u1.getId());
    Review v3 = new Review(3, "good3", r1.getId(), u1.getId());
    Review v4 = new Review(4, "good4", r1.getId(), u1.getId());

    reviewRepository.saveAll(Arrays.asList(v1, v2, v3, v4));

    room1 = roomRepository.findAll().iterator().next();
    roomDTO1 = RoomDTO.of(room1);
  }

  @Test
  void 리뷰_점수_숫자_조회하기() {
    log.debug("reviewPoint : {}", roomDTO1.getRating());
    assertThat(roomDTO1.getRating()).isEqualTo("2.50");

    log.debug("reviewPoint : {}", roomDTO1.getReviewCount());
    assertThat(roomDTO1.getReviewCount()).isEqualTo(4);
  }
}
