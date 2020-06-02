package kr.codesquad.airbnb11.controller.request;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CancelReservationRequest {

  private Integer roomId;
  private LocalDate startDate;
  private LocalDate endDate;

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("roomId", roomId)
        .append("startDate", startDate)
        .append("endDate", endDate)
        .toString();
  }
}
