package kr.codesquad.airbnb11.controller.request;

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
        .toString();
  }
}
