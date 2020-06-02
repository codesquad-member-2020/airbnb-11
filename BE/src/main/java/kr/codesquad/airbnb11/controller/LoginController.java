package kr.codesquad.airbnb11.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.codesquad.airbnb11.domain.user.User;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import kr.codesquad.airbnb11.service.JwtService;
import kr.codesquad.airbnb11.service.LoginService;
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

@Api(tags = "Login")
@RestController
@RequestMapping("/login")
public class LoginController {

  private static final Logger log = LoggerFactory.getLogger(LoginController.class);
  private final OAuthService authService;
  private final LoginService loginService;
  private final JwtService jwtService;

  public LoginController(OAuthService authService, LoginService loginService,
      JwtService jwtService) {
    this.authService = authService;
    this.loginService = loginService;
    this.jwtService = jwtService;
  }

  @ApiOperation(value = "", notes = "Github 로그인")
  @GetMapping
  public ResponseEntity<String> loginWithGithub(
      @CookieValue(value = "jwt", required = false) String jwt) {
    if (jwt != null) {
      log.debug("jwt 토큰 값: {}", jwt);
      log.debug("jwt에 저장된 값: {}", jwtService.getUserFromJws(jwt));
      return ResponseEntity.ok(jwtService.getUserFromJws(jwt).toString());
    }
    HttpHeaders headers = loginService.redirectToGithub();
    return new ResponseEntity<>("redirect", headers, HttpStatus.SEE_OTHER);
  }

  @ApiOperation(value = "", notes = "Github callBack")
  @GetMapping("/oauth")
  public ResponseEntity<String> oauthAuthentication(@RequestParam("code") String code) {
    String accessToken = authService.getTokenFromCode(code).getAccessToken();
    log.debug("AccessToken: {}", accessToken);

    User savedUser = loginService.insertUser(accessToken);
    UserDTO user = loginService.createUserDTO(savedUser);

    log.debug("JWT에 담길 User 정보: {}", user);

    String jws = jwtService.createUserJws(user);
    log.debug("jws: {}", jws);

    HttpHeaders headers = loginService.redirectWithCookie(jws);
    return new ResponseEntity<>("redirect", headers, HttpStatus.FOUND);
  }
}
