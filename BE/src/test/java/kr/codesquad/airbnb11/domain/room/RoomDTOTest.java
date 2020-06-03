package kr.codesquad.airbnb11.domain.room;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import kr.codesquad.airbnb11.common.utils.BigMoneyConverter;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RoomDTOTest {

  private static final Logger logger = LoggerFactory.getLogger(RoomDTOTest.class);

  @Test
  void RoomDTO_생성_테스트() {
    BigDecimal dailyPrice = new BigDecimal("43.00");
    Room room = Room.builder().dailyPrice(dailyPrice)
        .cleaningPrice(dailyPrice)
        .commission(dailyPrice)
        .servicePrice(dailyPrice)
        .build();
    RoomDTO roomDTO = RoomDTO.of(room);
    logger.debug("roomDTO: {}", roomDTO);

    BigMoney roomDTOMoneyConvertedToUSD = BigMoneyConverter
        .convertKRWToUSD(roomDTO.getDailyPrice());
    logger.debug("roomDTOMoney: {}", roomDTOMoneyConvertedToUSD);

    BigMoney USDMoney = BigMoney.of(CurrencyUnit.USD, dailyPrice)
        .rounded(2, RoundingMode.HALF_EVEN);
    logger.debug("USDMoney: {}", USDMoney);

    logger.debug("{}", roomDTO.getDailyPriceFormatted());

    assertThat(roomDTOMoneyConvertedToUSD).isEqualByComparingTo(USDMoney);
  }
}
