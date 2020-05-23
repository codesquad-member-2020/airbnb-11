package kr.codesquad.airbnb11;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Airbnb11ApplicationTests {

  private static final Logger log = LoggerFactory.getLogger(Airbnb11ApplicationTests.class);
  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    assertThat(applicationContext).isNotNull();
  }

  @Test
  void loggerLoads() {
    assertThat(log).isNotNull();
  }
}
