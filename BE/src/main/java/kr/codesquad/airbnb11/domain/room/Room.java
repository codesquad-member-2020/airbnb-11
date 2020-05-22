package kr.codesquad.airbnb11.domain.room;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

public class Room {

  private @Id Integer id;
  private Integer maxPersonCount;
  private String mainImage;
  private String title;
  private String description;
  private BigDecimal dailyPrice;
  private String country;

  public Integer getId() {
    return id;
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

  public BigDecimal getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(BigDecimal dailyPrice) {
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
        .append("description", description)
        .append("dailyPrice", dailyPrice)
        .append("country", country)
        .toString();
  }
}
