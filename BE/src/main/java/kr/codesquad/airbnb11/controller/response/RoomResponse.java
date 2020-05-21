package kr.codesquad.airbnb11.controller.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.money.BigMoney;

public class RoomResponse {

  private int id;
  private int maxPersonCount;
  private String mainImage;
  private String title;
  private BigMoney dailyPrice;
  private String country;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMaxPersonCount() {
    return maxPersonCount;
  }

  public void setMaxPersonCount(int maxPersonCount) {
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

  public BigMoney getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(BigMoney dailyPrice) {
    this.dailyPrice = dailyPrice;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("maxPersonCount", maxPersonCount)
        .append("mainImage", mainImage)
        .append("title", title)
        .append("dailyPrice", dailyPrice)
        .append("country", country)
        .toString();
  }
}
