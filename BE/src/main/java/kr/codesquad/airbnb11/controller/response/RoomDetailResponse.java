package kr.codesquad.airbnb11.controller.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import kr.codesquad.airbnb11.controller.request.RoomDetailRequest;
import kr.codesquad.airbnb11.domain.room.RoomDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoomDetailResponse {

  private int roomId;
  private int maxPersonCount;
  private int guestCount;
  private int infantCount;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private String title;
  private BigDecimal dailyPrice;
  private BigDecimal cleaningPrice;
  private BigDecimal servicePrice;
  private BigDecimal commission;
  private String country;
  private boolean isSuperHost;
  private BigDecimal latitude;
  private BigDecimal longitude;

  public RoomDetailResponse() {
  }

  public RoomDetailResponse(RoomDTO roomDTO, RoomDetailRequest roomDetailRequest) {
    this.roomId = roomDTO.getId();
    this.maxPersonCount = roomDTO.getMaxPersonCount();
    this.title = roomDTO.getTitle();
    this.dailyPrice = roomDTO.getDailyPriceFormatted();
    this.cleaningPrice = roomDTO.getCleaningPriceFormatted();
    this.servicePrice = roomDTO.getServicePriceFormatted();
    this.commission = roomDTO.getCommissionFormatted();
    this.country = roomDTO.getCountry();
    this.isSuperHost = roomDTO.getSuperHost();
    this.latitude = roomDTO.getLatitude();
    this.longitude = roomDTO.getLongitude();
    this.guestCount = roomDetailRequest.getGuestCount();
    this.infantCount = roomDetailRequest.getInfants();
    this.checkIn = roomDetailRequest.getCheckIn();
    this.checkOut = roomDetailRequest.getCheckOut();
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public int getMaxPersonCount() {
    return maxPersonCount;
  }

  public void setMaxPersonCount(int maxPersonCount) {
    this.maxPersonCount = maxPersonCount;
  }

  public int getGuestCount() {
    return guestCount;
  }

  public void setGuestCount(int guestCount) {
    this.guestCount = guestCount;
  }

  public int getInfantCount() {
    return infantCount;
  }

  public void setInfantCount(int infantCount) {
    this.infantCount = infantCount;
  }

  public LocalDate getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDate checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDate getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDate checkOut) {
    this.checkOut = checkOut;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getDailyPrice() {
    return dailyPrice;
  }

  public void setDailyPrice(BigDecimal dailyPrice) {
    this.dailyPrice = dailyPrice;
  }

  public BigDecimal getCleaningPrice() {
    return cleaningPrice;
  }

  public void setCleaningPrice(BigDecimal cleaningPrice) {
    this.cleaningPrice = cleaningPrice;
  }

  public BigDecimal getServicePrice() {
    return servicePrice;
  }

  public void setServicePrice(BigDecimal servicePrice) {
    this.servicePrice = servicePrice;
  }

  public BigDecimal getCommission() {
    return commission;
  }

  public void setCommission(BigDecimal commission) {
    this.commission = commission;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public boolean isSuperHost() {
    return isSuperHost;
  }

  public void setSuperHost(boolean superHost) {
    isSuperHost = superHost;
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("roomId", roomId)
        .append("maxPersonCount", maxPersonCount)
        .append("guestCount", guestCount)
        .append("infantCount", infantCount)
        .append("checkIn", checkIn)
        .append("checkOut", checkOut)
        .append("title", title)
        .append("dailyPrice", dailyPrice)
        .append("cleaningPrice", cleaningPrice)
        .append("servicePrice", servicePrice)
        .append("commission", commission)
        .append("country", country)
        .append("isSuperHost", isSuperHost)
        .append("latitude", latitude)
        .append("longitude", longitude)
        .toString();
  }

  public static final class Builder {

    private int roomId;
    private int maxPersonCount;
    private int guestCount;
    private int infantCount;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String title;
    private BigDecimal dailyPrice;
    private BigDecimal cleaningPrice;
    private BigDecimal servicePrice;
    private BigDecimal commission;
    private String country;
    private boolean isSuperHost;
    private BigDecimal latitude;
    private BigDecimal longitude;

    private Builder() {
    }

    public static Builder aRoomDetailResponse() {
      return new Builder();
    }

    public Builder roomId(int roomId) {
      this.roomId = roomId;
      return this;
    }

    public Builder maxPersonCount(int maxPersonCount) {
      this.maxPersonCount = maxPersonCount;
      return this;
    }

    public Builder guestCount(int guestCount) {
      this.guestCount = guestCount;
      return this;
    }

    public Builder infantCount(int infantCount) {
      this.infantCount = infantCount;
      return this;
    }

    public Builder checkIn(LocalDate checkIn) {
      this.checkIn = checkIn;
      return this;
    }

    public Builder checkOut(LocalDate checkOut) {
      this.checkOut = checkOut;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
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

    public Builder isSuperHost(boolean isSuperHost) {
      this.isSuperHost = isSuperHost;
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

    public RoomDetailResponse build() {
      RoomDetailResponse roomDetailResponse = new RoomDetailResponse();
      roomDetailResponse.setRoomId(roomId);
      roomDetailResponse.setMaxPersonCount(maxPersonCount);
      roomDetailResponse.setGuestCount(guestCount);
      roomDetailResponse.setInfantCount(infantCount);
      roomDetailResponse.setCheckIn(checkIn);
      roomDetailResponse.setCheckOut(checkOut);
      roomDetailResponse.setTitle(title);
      roomDetailResponse.setDailyPrice(dailyPrice);
      roomDetailResponse.setCleaningPrice(cleaningPrice);
      roomDetailResponse.setServicePrice(servicePrice);
      roomDetailResponse.setCommission(commission);
      roomDetailResponse.setCountry(country);
      roomDetailResponse.setLatitude(latitude);
      roomDetailResponse.setLongitude(longitude);
      roomDetailResponse.isSuperHost = this.isSuperHost;
      return roomDetailResponse;
    }
  }
}
