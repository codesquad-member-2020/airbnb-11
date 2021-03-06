package kr.codesquad.airbnb11.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import kr.codesquad.airbnb11.common.error.exception.UserNotFoundException;
import kr.codesquad.airbnb11.common.security.GithubKey;
import kr.codesquad.airbnb11.common.security.GithubPayload;
import kr.codesquad.airbnb11.common.security.GithubToken;
import kr.codesquad.airbnb11.common.security.GithubUser;
import kr.codesquad.airbnb11.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

  private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
  private static final String GITHUB_USER_API_URL = "https://api.github.com/user";
  private static final Logger log = LoggerFactory.getLogger(OAuthService.class);

  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate;
  private final GithubKey githubKey;

  public OAuthService(ObjectMapper objectMapper,
      RestTemplate restTemplate, GithubKey githubKey) {
    this.objectMapper = objectMapper;
    this.restTemplate = restTemplate;
    this.githubKey = githubKey;
  }

  public GithubToken getTokenFromCode(String code) {
    HttpEntity<GithubPayload> request = new HttpEntity<>(
        GithubPayload.of(githubKey, code));
    return restTemplate.postForEntity(GITHUB_ACCESS_TOKEN_URL, request, GithubToken.class)
        .getBody();
  }

  public User getUserInfoToToken(String token) {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.set("Authorization", "token " + token);

    GithubUser user = Optional.ofNullable(restTemplate
        .exchange(GITHUB_USER_API_URL, HttpMethod.GET, new HttpEntity<>(requestHeaders),
            GithubUser.class)
        .getBody())
        .orElseThrow(UserNotFoundException::new);
    user.setToken(token);

    // 공개된 user email이 없는 경우가 있어서 없는 경우 email API를 사용해서 채워줍니다.
    if (user.getEmail() == null) {
      user.setEmail(getEmailFromGitHub(requestHeaders));
    }
    return User.builder(user).build();
  }

  public String getEmailFromGitHub(HttpHeaders requestHeaders) {
    String email = restTemplate
        .exchange(GITHUB_USER_API_URL + "/emails", HttpMethod.GET, new HttpEntity<>(requestHeaders),
            String.class).getBody();

    try {
      JsonNode emailNode = objectMapper.readTree(email);
      for (JsonNode jsonNode : emailNode) {
        if (jsonNode.get("primary").asBoolean()) {
          return jsonNode.get("email").textValue();
        }
      }
    } catch (JsonProcessingException e) {
      log.error("Json 형식이 잘못 되었어요.", e);
    }
    return null;
  }
}
