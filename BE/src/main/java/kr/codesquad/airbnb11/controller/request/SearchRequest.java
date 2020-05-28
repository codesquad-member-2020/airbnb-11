package kr.codesquad.airbnb11.controller.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SearchRequest {

  private String checkIn;
  private String checkOut;
  private int priceMin;
  private int priceMax;
  private int adult;
  private int children;
  private int infants;

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

  public int getPriceMin() {
    return priceMin;
  }

  public void setPriceMin(int priceMin) {
    this.priceMin = priceMin;
  }

  public int getPriceMax() {
    return priceMax;
  }

  public void setPriceMax(int priceMax) {
    this.priceMax = priceMax;
  }

  public int getAdult() {
    return adult;
  }

  public void setAdult(int adult) {
    this.adult = adult;
  }

  public int getChildren() {
    return children;
  }

  public void setChildren(int children) {
    this.children = children;
  }

  public int getInfants() {
    return infants;
  }

  public void setInfants(int infants) {
    this.infants = infants;
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
