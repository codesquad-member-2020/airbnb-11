package kr.codesquad.airbnb11.common.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;
import kr.codesquad.airbnb11.common.security.GithubKey;
import kr.codesquad.airbnb11.controller.ReservationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public ReservationInterceptor reservationInterceptor() {
    return new ReservationInterceptor();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTION")
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowCredentials(true);
  }

  // MockMvc가 한글 인코딩이 깨져서 발생하는 현상을 해결하기 위한 방법(1)
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.stream()
        .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
        .findFirst()
        .ifPresent(converter -> ((MappingJackson2HttpMessageConverter) converter)
            .setDefaultCharset(UTF_8));
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(reservationInterceptor())
        .addPathPatterns("/reservations/**");
  }
}
