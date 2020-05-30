package kr.codesquad.airbnb11.service;

import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import kr.codesquad.airbnb11.domain.room.RoomDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  private static final Logger log = LoggerFactory.getLogger(RoomService.class);

  private final RoomDAO roomDAO;

  public RoomService(RoomDAO roomDAO) {
    this.roomDAO = roomDAO;
  }

  public SearchResponse findAvailableRooms(SearchRequest searchRequest) {
    SearchResponse searchResponse = new SearchResponse(
        roomDAO.selectRoomsWithSearchParams(searchRequest));
    log.debug("검색 결과: {}", searchResponse);
    return searchResponse;
  }
}
