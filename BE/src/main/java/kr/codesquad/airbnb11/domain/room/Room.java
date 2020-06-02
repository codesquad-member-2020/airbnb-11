package kr.codesquad.airbnb11.domain.room;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

public class Room {

  @Id
  private Integer id;
  private Integer maxPersonCount;
  private String mainImage;
  private String title;
  private String description;
  private BigDecimal dailyPrice;
  private String country;
  private Boolean isSuperHost;
  private double rating;
  private int reviewCount;

  public Room(Integer id, Integer maxPersonCount, String mainImage, String title,
      String description, BigDecimal dailyPrice, String country, Boolean isSuperHost, double rating,
      int reviewCount) {
    this.id = id;
    this.maxPersonCount = maxPersonCount;
    this.mainImage = mainImage;
    this.title = title;
    this.description = description;
    this.dailyPrice = dailyPrice;
    this.country = country;
    this.isSuperHost = isSuperHost;
    this.rating = rating;
    this.reviewCount = reviewCount;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer getId() {
    return id;
  }

  public Integer getMaxPersonCount() {
    return maxPersonCount;
  }

  public String getMainImage() {
    return mainImage;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getDailyPrice() {
    return dailyPrice;
  }

  public String getCountry() {
    return country;
  }

  public Boolean getSuperHost() {
    return isSuperHost;
  }

  public double getRating() {
    return rating;
  }

  public int getReviewCount() {
    return reviewCount;
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
        .append("isSuperHost", isSuperHost)
        .append("rating", rating)
        .append("reviewCount", reviewCount)
        .toString();
  }

  public static final class Builder {

    private Integer id;
    private Integer maxPersonCount;
    private String mainImage;
    private String title;
    private String description;
    private BigDecimal dailyPrice;
    private String country;
    private Boolean isSuperHost;
    private double rating;
    private int reviewCount;

    private Builder() {
    }

    public Builder id(Integer id) {
      this.id = id;
      return this;
    }

    public Builder maxPersonCount(Integer maxPersonCount) {
      this.maxPersonCount = maxPersonCount;
      return this;
    }

    public Builder mainImage(String mainImage) {
      this.mainImage = mainImage;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder dailyPrice(BigDecimal dailyPrice) {
      this.dailyPrice = dailyPrice;
      return this;
    }

    public Builder country(String country) {
      this.country = country;
      return this;
    }

    public Builder isSuperHost(Boolean isSuperHost) {
      this.isSuperHost = isSuperHost;
      return this;
    }

    public Builder rating(double rating) {
      this.rating = rating;
      return this;
    }

    public Builder reviewCount(int reviewCount) {
      this.reviewCount = reviewCount;
      return this;
    }

    public Room build() {
      return new Room(id, maxPersonCount, mainImage, title, description, dailyPrice, country,
          isSuperHost, rating, reviewCount);
    }
  }
}
