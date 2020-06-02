package kr.codesquad.airbnb11.controller.request;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoomDetailRequest {

  private int roomId;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private Integer adult;
  private Integer children;
  private Integer infants;

  public RoomDetailRequest(int roomId, LocalDate checkIn, LocalDate checkOut, Integer adult,
      Integer children, Integer infants) {
    this.roomId = roomId;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.adult = adult;
    this.children = children;
    this.infants = infants;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
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
  public int getGuestCount() {
    if (this.adult == null && this.children == null) {
      return 0;
    } else if (this.adult == null) {
      return this.children;
    } else if (this.children == null) {
      return this.adult;
    } else {
      return this.adult + this.children;
    }
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("roomId", roomId)
        .append("checkIn", checkIn)
        .append("checkOut", checkOut)
        .append("adult", adult)
        .append("children", children)
        .append("infants", infants)
        .toString();
  }
}
