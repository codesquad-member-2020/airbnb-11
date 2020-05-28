package kr.codesquad.airbnb11.common.config;

import kr.codesquad.airbnb11.common.security.GithubKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${github.client-id}")
  String clientId;
  @Value("${github.client-secret}")
  String clientSecret;

  @Bean
  public GithubKey githubKey() {
    return new GithubKey(clientId, clientSecret);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTION")
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
