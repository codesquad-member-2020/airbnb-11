package kr.codesquad.airbnb11.controller.request;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReservationRequest {

  private LocalDate checkIn;
  private LocalDate checkOut;
  private Integer roomId;

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

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  public int daysBetweenCheckInCheckOut() {
    return (int) ChronoUnit.DAYS.between(this.checkIn, this.checkOut) + 1;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("checkIn", checkIn)
        .append("checkOut", checkOut)
        .append("roomId", roomId)
        .toString();
  }
}
