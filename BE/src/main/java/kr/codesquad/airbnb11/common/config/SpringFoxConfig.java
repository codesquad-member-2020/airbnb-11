package kr.codesquad.airbnb11.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SpringFoxConfig {

  @Value("${host}")
  private String host;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .host(host)
        .select()
        .apis(RequestHandlerSelectors.basePackage("kr.codesquad.airbnb11.controller"))
        .paths(PathSelectors.ant("/rooms/*"))
        .build();
  }
}
