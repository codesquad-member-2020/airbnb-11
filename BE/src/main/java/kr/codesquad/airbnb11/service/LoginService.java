package kr.codesquad.airbnb11.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import kr.codesquad.airbnb11.common.error.exception.UserNotFoundException;
import kr.codesquad.airbnb11.common.security.GithubKey;
import kr.codesquad.airbnb11.domain.user.User;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import kr.codesquad.airbnb11.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LoginService {

  private static final Logger log = LoggerFactory.getLogger(LoginService.class);
  private final GithubKey githubKey;
  private final OAuthService authService;
  private final UserRepository userRepository;

  public LoginService(GithubKey githubKey, OAuthService authService,
      UserRepository userRepository) {
    this.githubKey = githubKey;
    this.authService = authService;
    this.userRepository = userRepository;
  }

  public HttpHeaders redirectToGithub() {
    HttpHeaders headers = new HttpHeaders();
    URI uri = UriComponentsBuilder.fromUriString("https://github.com/login/oauth/authorize")
        .queryParam("client_id", githubKey.getClientId())
        .queryParam("scope", "user")
        .build()
        .toUri();
    headers.setLocation(uri);
    return headers;
  }

  public HttpHeaders redirectWithCookie(String jwt) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

    int maxAge = 7 * 24 * 60 * 60;

    headers.add("Authorization", "Bearer " + jwt);
    headers.add("Set-Cookie", "jwt=" + jwt + "; Path=/" + "; Max-Age=" + maxAge + ";");
    headers.setLocation(URI.create("http://localhost:8080/login"));
    return headers;
  }


  public User insertUser(String token) {
    User user = authService.getUserInfoToToken(token);
    log.debug("DB 저장 전 User 정보: {}", user);
    User savedUser;

    try {
      savedUser = userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);
    } catch (UserNotFoundException e) {
      savedUser = userRepository.save(user);
    }

    log.debug("DB 저장 후 혹은 저장된 User 정보: {}", savedUser);
    return savedUser;
  }

  public UserDTO createUserDTO(String nickName, String email) {
    return UserDTO.of(nickName, email);
  }
}
