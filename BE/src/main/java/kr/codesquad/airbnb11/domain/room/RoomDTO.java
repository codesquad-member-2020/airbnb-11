package kr.codesquad.airbnb11.domain.room;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import kr.codesquad.airbnb11.common.utils.BigMoneyConverter;
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
  private BigMoney cleaningPrice;
  private BigMoney servicePrice;
  private BigMoney commission;
  private String country;
  private Boolean isSuperHost;
  private double rating;
  private int reviewCount;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private BigDecimal distance;

  private RoomDTO(Room room) {
    this.id = room.getId();
    this.maxPersonCount = room.getMaxPersonCount();
    this.mainImage = room.getMainImage();
    this.title = room.getTitle();
    this.description = room.getDescription();
    this.dailyPrice = BigMoneyConverter
        .convertUSDToKRW(BigMoney.of(CurrencyUnit.USD, room.getDailyPrice()));
    this.cleaningPrice = BigMoneyConverter
        .convertUSDToKRW(BigMoney.of(CurrencyUnit.USD, room.getCleaningPrice()));
    this.servicePrice = BigMoneyConverter
        .convertUSDToKRW(BigMoney.of(CurrencyUnit.USD, room.getServicePrice()));
    this.commission = BigMoneyConverter
        .convertUSDToKRW(BigMoney.of(CurrencyUnit.USD, room.getCommission()));
    this.country = room.getCountry();
    this.isSuperHost = room.getSuperHost();
    this.rating = room.getRating();
    this.reviewCount = room.getReviewCount();
    this.latitude = room.getLatitude();
    this.longitude = room.getLongitude();
    this.distance = room.getDistance();
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

  @JsonIgnore
  public BigMoney getCleaningPrice() {
    return cleaningPrice;
  }

  public void setCleaningPrice(BigMoney cleaningPrice) {
    this.cleaningPrice = cleaningPrice;
  }

  @JsonGetter("cleaningPrice")
  public BigDecimal getCleaningPriceFormatted() {
    return cleaningPrice.getAmountMajor();
  }

  @JsonIgnore
  public BigMoney getServicePrice() {
    return servicePrice;
  }

  public void setServicePrice(BigMoney servicePrice) {
    this.servicePrice = servicePrice;
  }

  @JsonGetter("servicePrice")
  public BigDecimal getServicePriceFormatted() {
    return servicePrice.getAmountMajor();
  }

  @JsonIgnore
  public BigMoney getCommission() {
    return commission;
  }

  public void setCommission(BigMoney commission) {
    this.commission = commission;
  }

  @JsonGetter("commission")
  public BigDecimal getCommissionFormatted() {
    return commission.getAmountMajor();
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Boolean getSuperHost() {
    return isSuperHost;
  }

  public void setSuperHost(Boolean superHost) {
    this.isSuperHost = superHost;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public int getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(int reviewCount) {
    this.reviewCount = reviewCount;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public BigDecimal getDistance() {
    return distance;
  }

  public void setDistance(BigDecimal distance) {
    this.distance = distance;
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
}
