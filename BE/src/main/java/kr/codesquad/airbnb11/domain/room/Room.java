package kr.codesquad.airbnb11.domain.room;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;

public class Room {

  @Id
  private Integer id;
  private Integer maxPersonCount;
  private String mainImage;
  private String title;
  private String description;
  private BigDecimal dailyPrice;
  private BigDecimal cleaningPrice;
  private BigDecimal servicePrice;
  private BigDecimal commission;
  private String country;
  private Boolean isSuperHost;
  private double rating;
  private int reviewCount;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private BigDecimal distance;


  public Room(Integer id, Integer maxPersonCount, String mainImage, String title,
      String description, BigDecimal dailyPrice, BigDecimal cleaningPrice,
      BigDecimal servicePrice, BigDecimal commission, String country, Boolean isSuperHost,
      double rating, int reviewCount, BigDecimal latitude, BigDecimal longitude,
      BigDecimal distance) {
    this.id = id;
    this.maxPersonCount = maxPersonCount;
    this.mainImage = mainImage;
    this.title = title;
    this.description = description;
    this.dailyPrice = dailyPrice;
    this.cleaningPrice = cleaningPrice;
    this.servicePrice = servicePrice;
    this.commission = commission;
    this.country = country;
    this.isSuperHost = isSuperHost;
    this.rating = rating;
    this.reviewCount = reviewCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.distance = distance;
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

  public BigDecimal getCleaningPrice() {
    return cleaningPrice;
  }

  public BigDecimal getServicePrice() {
    return servicePrice;
  }

  public BigDecimal getCommission() {
    return commission;
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

  public BigDecimal getLatitude() {
    return latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public BigDecimal getDistance() {
    return distance;
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
        .append("cleaningPrice", cleaningPrice)
        .append("servicePrice", servicePrice)
        .append("commission", commission)
        .append("country", country)
        .append("isSuperHost", isSuperHost)
        .append("rating", rating)
        .append("reviewCount", reviewCount)
        .append("latitude", latitude)
        .append("longitude", longitude)
        .append("distance", distance)
        .toString();
  }

  public static final class Builder {

    private Integer id;
    private Integer maxPersonCount;
    private String mainImage;
    private String title;
    private String description;
    private BigDecimal dailyPrice;
    private BigDecimal cleaningPrice;
    private BigDecimal servicePrice;
    private BigDecimal commission;
    private String country;
    private Boolean isSuperHost;
    private double rating;
    private int reviewCount;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal distance;

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

    public Builder cleaningPrice(BigDecimal cleaningPrice) {
      this.cleaningPrice = cleaningPrice;
      return this;
    }

    public Builder servicePrice(BigDecimal servicePrice) {
      this.servicePrice = servicePrice;
      return this;
    }

    public Builder commission(BigDecimal commission) {
      this.commission = commission;
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

    public Builder latitude(BigDecimal latitude) {
      this.latitude = latitude;
      return this;
    }

    public Builder longitude(BigDecimal longitude) {
      this.longitude = longitude;
      return this;
    }

    public Builder distance(BigDecimal distance) {
      this.distance = distance;
      return this;
    }

    public Room build() {
      return new Room(id, maxPersonCount, mainImage, title, description, dailyPrice, cleaningPrice,
          servicePrice, commission, country, isSuperHost, rating, reviewCount, latitude, longitude,
          distance);
    }
  }
}
