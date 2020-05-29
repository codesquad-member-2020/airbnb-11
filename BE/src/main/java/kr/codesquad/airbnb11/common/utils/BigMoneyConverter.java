package kr.codesquad.airbnb11.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

public class BigMoneyConverter {

  private static final CurrencyUnit CURRENCY_UNIT_KRW = CurrencyUnit.of("KRW");
  private static final BigDecimal KRW_TO_USD_EXCHANGE_RATE = new BigDecimal("1234.00");
  private static final BigDecimal USD_TO_KRW_EXCHANGE_RATE = new BigDecimal("0.000810373");

  private BigMoneyConverter() {
  }

  public static BigMoney convertUSDToKRW(BigMoney usdBigMoney) {
    return usdBigMoney.convertedTo(CURRENCY_UNIT_KRW, KRW_TO_USD_EXCHANGE_RATE)
        .rounded(0, RoundingMode.HALF_EVEN);
  }

  public static BigMoney convertKRWToUSD(BigMoney krwBigMoney) {
    return krwBigMoney.convertedTo(CurrencyUnit.USD, USD_TO_KRW_EXCHANGE_RATE)
        .rounded(2, RoundingMode.HALF_EVEN);
  }
}
