package kr.codesquad.airbnb11.domain.reservation;

import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Reservation {

  @Id
  private Integer id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer roomId;
  private Integer guest;

  public Reservation(LocalDateTime startDate, LocalDateTime endDate, Integer roomId,
      Integer guest) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.roomId = roomId;
    this.guest = guest;
  }

  @PersistenceConstructor
  public Reservation(Integer id, LocalDateTime startDate, LocalDateTime endDate,
      Integer roomId, Integer guest) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.roomId = roomId;
    this.guest = guest;
  }

  public Integer getId() {
    return id;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public Integer getGuest() {
    return guest;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("startDate", startDate)
        .append("endDate", endDate)
        .append("roomId", roomId)
        .append("guest", guest)
        .toString();
  }
}
