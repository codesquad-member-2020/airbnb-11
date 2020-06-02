package kr.codesquad.airbnb11.common.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BigMoneyConverterTest {

  private static final Logger log = LoggerFactory.getLogger(BigMoneyConverter.class);

  @Test
  void KRW에서_USD로_변환_테스트() {
    BigMoney KRWBigMoney = BigMoney.of(CurrencyUnit.of("KRW"), new BigDecimal("37020"));
    log.debug("KRWBigMoney: {}", KRWBigMoney);

    BigMoney USDBigMoney = BigMoneyConverter.convertKRWToUSD(KRWBigMoney);

    log.debug("USDBigMoney: {}", USDBigMoney);
    assertThat(USDBigMoney.getAmount()).isEqualByComparingTo(new BigDecimal("30.00"));
  }

  @Test
  void USD에서_KRW로_변환_테스트() {
    BigMoney USDBigMoney = BigMoney.of(CurrencyUnit.of("USD"), new BigDecimal("30.00"));
    log.debug("USDBigMoney: {}", USDBigMoney);

    BigMoney KRWBigMoney = BigMoneyConverter.convertUSDToKRW(USDBigMoney);

    log.debug("KRWBigMoney: {}", KRWBigMoney);
    assertThat(KRWBigMoney.getAmount()).isEqualByComparingTo(new BigDecimal("37020"));
  }
}
