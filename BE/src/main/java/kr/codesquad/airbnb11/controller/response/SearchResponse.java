package kr.codesquad.airbnb11.controller.response;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.airbnb11.domain.room.Room;
import kr.codesquad.airbnb11.domain.room.RoomDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SearchResponse {

  private int roomsCount;
  private List<RoomResponse> rooms;
  private List<BigDecimal> prices;

  public SearchResponse(List<Room> rooms) {
    this.roomsCount = rooms.size();
    this.rooms = rooms.stream()
        .map(RoomDTO::of)
        .map(RoomResponse::new)
        .collect(Collectors.toList());
    this.prices = this.rooms.stream()
        .map(RoomResponse::getDailyPrice)
        .collect(Collectors.toList());
    Collections.sort(this.prices);
  }

  public int getRoomsCount() {
    return roomsCount;
  }

  public void setRoomsCount(int roomsCount) {
    this.roomsCount = roomsCount;
  }

  public List<RoomResponse> getRooms() {
    return rooms;
  }

  public void setRooms(List<RoomResponse> rooms) {
    this.rooms = rooms;
  }

  public List<BigDecimal> getPrices() {
    return prices;
  }

  public void setPrices(List<BigDecimal> prices) {
    this.prices = prices;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("roomsCount", roomsCount)
        .append("rooms", rooms)
        .append("prices", prices)
        .toString();
  }
}
