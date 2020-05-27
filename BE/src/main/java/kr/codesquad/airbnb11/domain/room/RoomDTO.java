package kr.codesquad.airbnb11.domain.room;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

public class RoomDTO {

  private Integer id;
  private Integer maxPersonCount;
  private String mainImage;
  private String title;
  private String description;
  private BigMoney dailyPrice;
  private String country;

  private RoomDTO(Room room) {
    this.id = room.getId();
    this.maxPersonCount = room.getMaxPersonCount();
    this.mainImage = room.getMainImage();
    this.title = room.getTitle();
    this.description = room.getDescription();
    this.dailyPrice = BigMoney.of(CurrencyUnit.USD, room.getDailyPrice())
        .convertedTo(CurrencyUnit.of("KRW"), new BigDecimal("1234.00"))
        .rounded(0, RoundingMode.HALF_EVEN);
    this.country = room.getCountry();
  }

  public static RoomDTO of(Room room) {
    return new RoomDTO(room);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMaxPersonCount() {
    return maxPersonCount;
  }

  public void setMaxPersonCount(Integer maxPersonCount) {
    this.maxPersonCount = maxPersonCount;
  }

  public String getMainImage() {
    return mainImage;
  }

  public void setMainImage(String mainImage) {
    this.mainImage = mainImage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @JsonIgnore
  public BigMoney getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(BigMoney dailyPrice) {
    this.dailyPrice = dailyPrice;
  }

  @JsonGetter("dailyPrice")
  public BigDecimal getDailyPriceFormatted() {
    return dailyPrice.getAmountMajor();
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("maxPersonCount", maxPersonCount)
        .append("mainImage", mainImage)
        .append("title", title)
        .append("description", description)
        .append("dailyPrice", dailyPrice)
        .append("country", country)
        .toString();
  }
}
