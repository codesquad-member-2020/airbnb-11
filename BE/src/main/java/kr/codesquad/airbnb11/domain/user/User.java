package kr.codesquad.airbnb11.domain.user;

import kr.codesquad.airbnb11.common.security.GithubUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class User {

  @Id
  private final Integer id;
  private final Boolean isAdmin;
  private final String email;
  private final String nickname;
  private final String githubToken;
  private Boolean isHost;
  private Boolean isSuperHost;

  @PersistenceConstructor
  private User(Integer id, Boolean isHost, Boolean isSuperHost, Boolean isAdmin,
      String email, String nickname, String githubToken) {
    this.id = id;
    this.isHost = isHost;
    this.isSuperHost = isSuperHost;
    this.isAdmin = isAdmin;
    this.email = email;
    this.nickname = nickname;
    this.githubToken = githubToken;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(GithubUser user) {
    return new Builder(user);
  }

  public Integer getId() {
    return id;
  }

  public Boolean getHost() {
    return isHost;
  }

  public Boolean getSuperHost() {
    return isSuperHost;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public String getEmail() {
    return email;
  }

  public String getNickname() {
    return nickname;
  }

  public String getGithubToken() {
    return githubToken;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("isHost", isHost)
        .append("isSuperHost", isSuperHost)
        .append("isAdmin", isAdmin)
        .append("email", email)
        .append("nickname", nickname)
        .append("githubToken", githubToken)
        .toString();
  }

  public static final class Builder {

    private Boolean isHost;
    private Boolean isSuperHost;
    private Boolean isAdmin;
    private String email;
    private String nickname;
    private String githubToken;

    private Builder() {
    }

    private Builder(GithubUser githubUser) {
      this.isHost = false;
      this.isSuperHost = false;
      this.isAdmin = false;
      this.email = githubUser.getEmail();
      this.nickname = githubUser.getName();
      this.githubToken = githubUser.getToken();
    }

    public Builder isHost(Boolean isHost) {
      this.isHost = isHost;
      return this;
    }

    public Builder isSuperHost(Boolean isSuperHost) {
      this.isSuperHost = isSuperHost;
      return this;
    }

    public Builder isAdmin(Boolean isAdmin) {
      this.isAdmin = isAdmin;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder nickname(String nickname) {
      this.nickname = nickname;
      return this;
    }

    public Builder githubToken(String githubToken) {
      this.githubToken = githubToken;
      return this;
    }

    public User build() {
      return new User(null, isHost, isSuperHost, isAdmin, email, nickname, githubToken);
    }
  }
}
