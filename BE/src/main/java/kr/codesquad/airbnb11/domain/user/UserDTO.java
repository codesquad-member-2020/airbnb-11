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

  public static UserDTO of(String nickname, String email) {
    return new UserDTO(nickname, email);
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
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("nickname", nickname)
        .append("email", email)
        .toString();
  }
}
