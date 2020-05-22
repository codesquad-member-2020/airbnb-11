package kr.codesquad.airbnb11.controller.response;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchResponse {

  private int roomsCount;
  private List<RoomResponse> rooms;

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

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("roomsCount", roomsCount)
        .append("rooms", rooms)
        .toString();
  }
}
