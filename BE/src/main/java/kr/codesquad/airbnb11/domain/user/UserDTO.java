package kr.codesquad.airbnb11.domain.user;

import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserDTO {

  private final String nickname;
  private final String email;

  private UserDTO(String nickname, String email) {
    this.nickname = nickname;
    this.email = email;
  }

  private UserDTO(Map<String, String> userMap) {
    this.nickname = userMap.get("nickname");
    this.email = userMap.get("email");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static UserDTO of(Map<String, String> userMap) {
    return new UserDTO(userMap);
  }

  public String getNickname() {
    return nickname;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("nickname", nickname)
        .append("email", email)
        .toString();
  }

  public static final class Builder {

    private String nickname;
    private String email;

    private Builder() {
    }

    public Builder nickname(String nickname) {
      this.nickname = nickname;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public UserDTO build() {
      return new UserDTO(nickname, email);
    }
  }
}
