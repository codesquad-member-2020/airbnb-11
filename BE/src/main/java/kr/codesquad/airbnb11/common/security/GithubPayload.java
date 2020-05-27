package kr.codesquad.airbnb11.common.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubPayload {

  @JsonProperty("client_id")
  private final String clientId = System.getenv("GITHUB_CLIENT_ID");

  @JsonProperty("client_secret")
  private final String clientSecret = System.getenv("GITHUB_CLIENT_SECRET");

  private final String code;

  private GithubPayload(String code) {
    this.code = code;
  }

  public static GithubPayload valueOf(String code) {
    return new GithubPayload(code);
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getCode() {
    return code;
  }
}
