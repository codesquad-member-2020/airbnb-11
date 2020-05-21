package kr.codesquad.airbnb11.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rooms")
@RestController
public class RoomRestController {

  private static final Logger log = LoggerFactory.getLogger(RoomRestController.class);

  // TODO: Request 객체 정의하기
  @GetMapping("/search")
  public void searchRooms(@RequestParam(required = false) String aaa) {
    log.debug("hello");
  }

}
