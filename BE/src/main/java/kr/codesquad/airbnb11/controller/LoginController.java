package kr.codesquad.airbnb11.controller;

import java.net.URI;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.airbnb11.common.security.GithubKey;
import kr.codesquad.airbnb11.common.security.JwtUtil;
import kr.codesquad.airbnb11.domain.user.User;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import kr.codesquad.airbnb11.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class LoginController {

  private static final Logger log = LoggerFactory.getLogger(LoginController.class);
  private final OAuthService authService;
  private final JwtUtil jwtUtil;
  private final GithubKey githubKey;

  public LoginController(OAuthService authService,
      JwtUtil jwtUtil, GithubKey githubKey) {
    this.authService = authService;
    this.jwtUtil = jwtUtil;
    this.githubKey = githubKey;
  }

  @GetMapping
  public ResponseEntity<String> loginWithGithub(
      @CookieValue(value = "jwt", required = false) String jwt) {
    if (jwt != null) {
      log.debug("jwt 토큰 값: {}", jwt);
      log.debug("jwt에 저장된 값: {}", jwtUtil.getUserFromJws(jwt));
      return ResponseEntity.ok("ok");
    }

    HttpHeaders headers = new HttpHeaders();
    URI uri = UriComponentsBuilder.fromUriString("https://github.com/login/oauth/authorize")
        .queryParam("client_id", githubKey.getClientId())
        .queryParam("scope", "user")
        .build()
        .toUri();
    headers.setLocation(uri);
    return new ResponseEntity<>("redirect", headers, HttpStatus.SEE_OTHER);
  }

  @GetMapping("/oauth")
  public ResponseEntity<String> oauthAuthentication(@RequestParam("code") String code,
      HttpServletResponse response) {
    String accessToken = authService.getTokenFromCode(code).getAccessToken();
    log.debug("AccessToken: {}", accessToken);

    User saved = authService.insertUser(accessToken);

    UserDTO user = UserDTO.builder()
        .email(saved.getEmail())
        .nickname(saved.getNickname())
        .build();

    log.debug("JWT에 담길 User 정보: {}", user);

    String jws = jwtUtil.createUserJws(user);
    log.debug("jws: {}", jws);

    Cookie cookie = new Cookie("jwt", jws);
    cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
    cookie.setPath("/");

    response.addCookie(cookie);
    response.setHeader("Location", "http://localhost:8080");
    return new ResponseEntity<>("redirect", HttpStatus.FOUND);
  }

}
