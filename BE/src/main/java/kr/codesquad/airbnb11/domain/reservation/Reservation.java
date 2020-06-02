package kr.codesquad.airbnb11.domain.reservation;

import java.time.LocalDate;
import kr.codesquad.airbnb11.controller.request.ReservationRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Reservation {

  @Id
  private Integer id;
  private LocalDate reservationDate;
  private Integer roomId;
  private Integer guestId;

  public Reservation(LocalDate reservationDate, Integer roomId, Integer guestId) {
    this.reservationDate = reservationDate;
    this.roomId = roomId;
    this.guestId = guestId;
  }

  @PersistenceConstructor
  public Reservation(Integer id, LocalDate reservationDate, Integer roomId, Integer guestId) {
    this.id = id;
    this.reservationDate = reservationDate;
    this.roomId = roomId;
    this.guestId = guestId;
  }

  public static Reservation of(LocalDate accommodationDate, ReservationRequest request,
      int userId) {
    return new Reservation(accommodationDate, request.getRoomId(), userId);
  }

  public Integer getId() {
    return id;
  }

  public LocalDate getReservationDate() {
    return reservationDate;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public Integer getGuestId() {
    return guestId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("date", reservationDate)
        .append("roomId", roomId)
        .append("guest", guestId)
        .toString();
  }
}
