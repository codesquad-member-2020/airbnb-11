package kr.codesquad.airbnb11.domain.review;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Review {

  @Id
  private Integer id;
  private Integer rating;
  private String content;
  private Integer roomId;
  private Integer guestId;

  public Review(Integer rating, String content, Integer roomId, Integer guestId) {
    this.rating = rating;
    this.content = content;
    this.roomId = roomId;
    this.guestId = guestId;
  }

  @PersistenceConstructor
  private Review(Integer id, Integer rating, String content, Integer roomId, Integer guestId) {
    this.id = id;
    this.rating = rating;
    this.content = content;
    this.roomId = roomId;
    this.guestId = guestId;
  }

  public Integer getId() {
    return id;
  }

  public Integer getRating() {
    return rating;
  }

  public String getContent() {
    return content;
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
        .append("rating", rating)
        .append("content", content)
        .append("roomId", roomId)
        .append("guestId", guestId)
        .toString();
  }
}
