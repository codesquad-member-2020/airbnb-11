package kr.codesquad.airbnb11.common.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GithubPayload {

  @JsonProperty("client_id")
  private final String clientId;

  @JsonProperty("client_secret")
  private final String clientSecret;

  private final String code;

  private GithubPayload(String clientId, String clientSecret, String code) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.code = code;
  }

  public static GithubPayload of(String clientId, String clientSecret, String code) {
    return new GithubPayload(clientId, clientSecret, code);
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("clientId", clientId)
        .append("clientSecret", clientSecret)
        .append("code", code)
        .toString();
  }
}
