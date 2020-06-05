package kr.codesquad.airbnb11.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import kr.codesquad.airbnb11.controller.response.SearchResponse;
import kr.codesquad.airbnb11.domain.room.Room;
import kr.codesquad.airbnb11.domain.user.User;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import kr.codesquad.airbnb11.service.JwtService;
import kr.codesquad.airbnb11.service.LoginService;
import kr.codesquad.airbnb11.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@WebMvcTest(controllers = RoomRestController.class)
class RoomRestControllerTest {

  private static final Logger log = LoggerFactory.getLogger(RoomRestControllerTest.class);

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RoomService roomService;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private LoginService loginService;

//  @Autowired
//  private WebApplicationContext wac;
//
//  // MockMvc가 한글 인코딩이 깨져서 발생하는 현상을 해결하기 위한 방법(2)
//  @BeforeEach
//  void setUp() {
//    mockMvc = webAppContextSetup(wac).addFilter(((request, response, chain) -> {
//      response.setCharacterEncoding("UTF-8");
//      chain.doFilter(request, response);
//    })).build();
//  }

  @Test
  @DisplayName("rooms search API test")
  void roomsSearchApiTest() throws Exception {
    // given
    Room room1 = Room.builder().id(1).country("대한민국").dailyPrice(new BigDecimal("20"))
        .cleaningPrice(new BigDecimal("5")).servicePrice(new BigDecimal("5"))
        .commission(new BigDecimal("10")).description("").title("1번").mainImage("image")
        .maxPersonCount(2).isSuperHost(true).build();
    Room room2 = Room.builder().id(2).country("프랑스").dailyPrice(new BigDecimal("35"))
        .cleaningPrice(new BigDecimal("10")).servicePrice(new BigDecimal("8"))
        .commission(new BigDecimal("13")).description("").title("2번").mainImage("image")
        .maxPersonCount(4).isSuperHost(true).build();
    Room room3 = Room.builder().id(3).country("미국").dailyPrice(new BigDecimal("100"))
        .cleaningPrice(new BigDecimal("20")).servicePrice(new BigDecimal("7"))
        .commission(new BigDecimal("11")).description("").title("3번").mainImage("image")
        .maxPersonCount(4).isSuperHost(false).build();
    Room room4 = Room.builder().id(4).country("미국").dailyPrice(new BigDecimal("50"))
        .cleaningPrice(new BigDecimal("25")).servicePrice(new BigDecimal("3"))
        .commission(new BigDecimal("6")).description("").title("4번").mainImage("image")
        .maxPersonCount(4).isSuperHost(false).build();
    SearchRequest searchRequest = new SearchRequest();

    List<Room> rooms = Arrays.asList(room1, room2, room3, room4);
    SearchResponse searchResponse = new SearchResponse(rooms.size(), rooms);
    log.debug("테스트 검색 결과: {}", searchResponse);

    // when
    when(roomService.findAvailableRooms(searchRequest)).thenReturn(searchResponse);

    // then
    MockHttpServletRequestBuilder requestBuilder = get("/rooms/search")
        .flashAttr("searchRequest", searchRequest)
        .contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.roomsCount", is(rooms.size())))
        .andExpect(jsonPath("$.rooms[0].id", is(room1.getId())))
        .andExpect(jsonPath("$.rooms[1].country", is(room2.getCountry())))
        .andExpect(jsonPath("$.rooms[2].maxPersonCount", is(room3.getMaxPersonCount())))
        .andExpect(jsonPath("$.rooms[3].title", is(room4.getTitle())))
        .andExpect(jsonPath("$.prices[0]", is(searchResponse.getPrices().get(0).intValue())))
        .andExpect(jsonPath("$.prices[1]", is(searchResponse.getPrices().get(1).intValue())))
        .andExpect(jsonPath("$.prices[2]", is(searchResponse.getPrices().get(2).intValue())))
        .andExpect(jsonPath("$.prices[3]", is(searchResponse.getPrices().get(3).intValue())));
  }

  @Test
  @DisplayName("rooms reserve API test")
  void roomsReserveApiTest() throws Exception {
    // given
    String reservationRequestJsonString = "{\"checkIn\": \"2020-05-30\", \"checkOut\": \"2020-05-31\", \"roomId\": 1}";
    log.debug("json String: {}", reservationRequestJsonString);

    User user = new User(1, false, false, false, "test@test.com", "test", "");
    UserDTO userDTO = UserDTO.of(user.getNickname(), user.getEmail());
    String tokenString = "token";

    when(jwtService.createUserJws(userDTO)).thenReturn(tokenString);
    when(jwtService.getUserFromJws(tokenString)).thenReturn(userDTO);
    when(loginService.isUserExist(userDTO)).thenReturn(true);

    //when
    String token = jwtService.createUserJws(userDTO);
    Cookie cookie = new Cookie("jwt", token);

    // then
    MockHttpServletRequestBuilder requestBuilder = post("/rooms/reservation")
        .content(reservationRequestJsonString)
        .contentType(MediaType.APPLICATION_JSON)
        .cookie(cookie);

    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is(true)))
        .andExpect(jsonPath("$.response", is("예약이 정상적으로 완료되었습니다.")))
        .andExpect(jsonPath("$.error", is(nullValue())));
  }
}
