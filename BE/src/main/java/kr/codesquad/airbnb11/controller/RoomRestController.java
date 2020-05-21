package kr.codesquad.airbnb11.controller;

import kr.codesquad.airbnb11.controller.request.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rooms")
@RestController
public class RoomRestController {

  private static final Logger log = LoggerFactory.getLogger(RoomRestController.class);

  @GetMapping("/search")
  public void searchRooms(@RequestBody(required = false) SearchRequest searchRequest) {
    log.debug("searchRequest: {}", searchRequest);
  }

}
