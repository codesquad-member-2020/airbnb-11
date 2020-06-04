package kr.codesquad.airbnb11.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.airbnb11.common.error.exception.LoginRequiredException;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import kr.codesquad.airbnb11.service.JwtService;
import kr.codesquad.airbnb11.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ReservationInterceptor extends HandlerInterceptorAdapter {

  private static Logger log = LoggerFactory.getLogger(ReservationInterceptor.class);

  @Autowired
  private JwtService jwtService;

  @Autowired
  private LoginService loginService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    String token = "";
    Cookie[] cookies = request.getCookies();

    if (cookies == null || cookies.length < 1) {
      throw new LoginRequiredException("로그인이 필요합니다");
    }

    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("jwt")) {
        token = cookie.getValue();
        log.debug("token : {}", token);
        break;
      }
    }

    UserDTO user = jwtService.getUserFromJws(token);
    log.debug("user : {}", user);

    if (loginService.findUserByEmail(user)) {
      return true;
    }
    return false;
  }
}
