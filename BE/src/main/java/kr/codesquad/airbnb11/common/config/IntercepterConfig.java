package kr.codesquad.airbnb11.common.config;

import kr.codesquad.airbnb11.common.security.ReservationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

  private final ReservationInterceptor reservationInterceptor;

  public IntercepterConfig(
      ReservationInterceptor reservationInterceptor) {
    this.reservationInterceptor = reservationInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(reservationInterceptor)
        .addPathPatterns("/reservations/**")
        .addPathPatterns("/rooms/reservation");
  }
}

