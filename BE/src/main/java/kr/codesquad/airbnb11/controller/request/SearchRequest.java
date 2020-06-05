package kr.codesquad.airbnb11.controller.request;

import static kr.codesquad.airbnb11.common.SQLKt.OFFSET_COUNT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.lang.Nullable;

public class SearchRequest {

  private String checkIn;
  private String checkOut;
  private Integer priceMin;
  private Integer priceMax;
  private Integer adult;
  private Integer children;
  private Integer infants;
  private int page;
  private BigDecimal latitude = new BigDecimal("37.49081965861202");
  private BigDecimal longitude = new BigDecimal("127.03341954607245");

  public SearchRequest() {
    page = 1;
  }

  public SearchRequest(String checkIn, String checkOut, Integer priceMin, Integer priceMax,
      Integer adult, Integer children, Integer infants, int page, BigDecimal latitude,
      BigDecimal longitude) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.priceMin = priceMin;
    this.priceMax = priceMax;
    this.adult = adult;
    this.children = children;
    this.infants = infants;
    this.page = page;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(String checkIn) {
    this.checkIn = checkIn;
  }

  public String getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(String checkOut) {
    this.checkOut = checkOut;
  }

  public Integer getPriceMin() {
    return priceMin;
  }

  public void setPriceMin(Integer priceMin) {
    this.priceMin = priceMin;
  }

  public Integer getPriceMax() {
    return priceMax;
  }

  public void setPriceMax(Integer priceMax) {
    this.priceMax = priceMax;
  }

  public Integer getAdult() {
    return adult;
  }

  public void setAdult(Integer adult) {
    this.adult = adult;
  }

  public Integer getChildren() {
    return children;
  }

  public void setChildren(Integer children) {
    this.children = children;
  }

  public Integer getInfants() {
    return infants;
  }

  public void setInfants(Integer infants) {
    this.infants = infants;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
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

  // 유아는 guest인원에 포함하지 않음.
  @Nullable
  public Integer getMinPersonCount() {
    if (this.adult == null && this.children == null) {
      return null;
    } else if (this.adult == null) {
      return this.children;
    } else if (this.children == null) {
      return this.adult;
    } else {
      return this.adult + this.children;
    }
  }

  public Map<String, Object> getParameterMap() {
    Map<String, Object> parameterMap = new HashMap<>();
    parameterMap.put("checkIn", this.getCheckIn());
    parameterMap.put("checkOut", this.getCheckOut());
    parameterMap.put("priceMin", this.getPriceMin());
    parameterMap.put("priceMax", this.getPriceMax());
    parameterMap.put("minPersonCount", this.getMinPersonCount());
    parameterMap.put("offset", (OFFSET_COUNT * (this.getPage() - 1))); // MySQL LIMIT OFFSET
    parameterMap.put("latitude", this.getLatitude());
    parameterMap.put("longitude", this.getLongitude());
    return parameterMap;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("checkIn", checkIn)
        .append("checkOut", checkOut)
        .append("priceMin", priceMin)
        .append("priceMax", priceMax)
        .append("adult", adult)
        .append("children", children)
        .append("infants", infants)
        .append("page", page)
        .append("latitude", latitude)
        .append("longitude", longitude)
        .toString();
  }
}
