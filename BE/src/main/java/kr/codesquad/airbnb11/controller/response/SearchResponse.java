package kr.codesquad.airbnb11.controller.response;

import static kr.codesquad.airbnb11.common.SQLKt.OFFSET_COUNT;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.airbnb11.common.utils.BigMoneyConverter;
import kr.codesquad.airbnb11.domain.room.Room;
import kr.codesquad.airbnb11.domain.room.RoomDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

public class SearchResponse {

  private Integer roomsCount;
  private List<RoomResponse> rooms;
  private List<BigDecimal> prices;

  public SearchResponse(Integer roomsCount, List<Room> rooms) {
    this.roomsCount = roomsCount;
    this.rooms = rooms.stream()
        .map(RoomDTO::of)
        .map(RoomResponse::new)
        .collect(Collectors.toList());
    this.prices = this.rooms.stream()
        .map(RoomResponse::getDailyPrice)
        .collect(Collectors.toList());
    Collections.sort(this.prices);
  }

  public SearchResponse(Integer roomsCount, List<Room> rooms, List<BigDecimal> prices) {
    this.roomsCount = roomsCount;
    this.rooms = rooms.stream()
        .map(RoomDTO::of)
        .map(RoomResponse::new)
        .collect(Collectors.toList());
    this.prices = prices.stream()
        .map(p -> BigMoney.of(CurrencyUnit.USD, p))
        .map(BigMoneyConverter::convertUSDToKRW)
        .map(BigMoney::getAmountMajor)
        .collect(Collectors.toList());
    Collections.sort(this.prices);
  }

  public Integer getRoomsCount() {
    return roomsCount;
  }

  public void setRoomsCount(Integer roomsCount) {
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

  public int getMaxPage() {
    return (roomsCount / OFFSET_COUNT) + (roomsCount % OFFSET_COUNT > 0 ? 1 : 0);
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
