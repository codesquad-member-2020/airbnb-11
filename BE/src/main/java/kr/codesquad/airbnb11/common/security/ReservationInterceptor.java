package kr.codesquad.airbnb11.common.security;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.airbnb11.common.error.exception.LoginRequiredException;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import kr.codesquad.airbnb11.service.JwtService;
import kr.codesquad.airbnb11.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ReservationInterceptor extends HandlerInterceptorAdapter {

  private static final Logger log = LoggerFactory.getLogger(ReservationInterceptor.class);

  private final JwtService jwtService;
  private final LoginService loginService;

  public ReservationInterceptor(JwtService jwtService, LoginService loginService) {
    this.jwtService = jwtService;
    this.loginService = loginService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    Cookie[] cookies = request.getCookies();

    if (cookies == null || cookies.length < 1) {
      throw new LoginRequiredException();
    }

    String token = Arrays.stream(cookies)
        .filter(c -> c.getName().equals("jwt"))
        .map(Cookie::getValue)
        .reduce(String::concat)
        .orElse("");

    UserDTO user = jwtService.getUserFromJws(token);
    log.debug("user : {}", user);

    if (loginService.isUserExist(user)) {
      return true;
    }
    return false;
  }
}
