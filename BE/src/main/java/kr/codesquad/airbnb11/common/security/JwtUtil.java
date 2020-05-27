package kr.codesquad.airbnb11.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kr.codesquad.airbnb11.common.error.exception.LoginRequiredException;
import kr.codesquad.airbnb11.domain.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final String USER_JWT_KEY = "user";
  private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

  public String createJws(String jwtKey, Object data) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");
    headers.put("alg", "HS256");

    Map<String, Object> payloads = new HashMap<>();
    payloads.put(jwtKey, data);

    return Jwts.builder()
        .setHeader(headers)
        .setClaims(payloads)
        .signWith(key)
        .compact();
  }

  public String createJws(Map<String, Object> payloads) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");
    headers.put("alg", "HS256");

    return Jwts.builder()
        .setHeader(headers)
        .setClaims(payloads)
        .signWith(key)
        .compact();
  }

  public String createUserJws(UserDTO data) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");
    headers.put("alg", "HS256");

    Map<String, UserDTO> payloads = new HashMap<>();
    payloads.put(USER_JWT_KEY, data);

    return Jwts.builder()
        .setHeader(headers)
        .setClaims(payloads)
        .signWith(key)
        .compact();
  }

  public Map<String, Object> getDataFromJws(String jwtKey, String jws) {
    Jws<Claims> claims;
    try {
      claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jws);
      return (LinkedHashMap<String, Object>) claims.getBody().get(jwtKey);
    } catch (JwtException ex) {
      log.error("인증되지 않은 jwt token입니다. jws: {}", jws);
      // Custom Exception Unauthorized Exception
      throw new LoginRequiredException("인증되지 않은 jwt token입니다.");
    }
  }

  public UserDTO getUserFromJws(String jws) {
    Jws<Claims> claims;
    try {
      claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jws);
      return UserDTO.of((LinkedHashMap<String, String>) claims.getBody().get(USER_JWT_KEY));
    } catch (JwtException ex) {
      log.error("인증되지 않은 jwt token입니다. jws: {}", jws);
      // Custom Exception Unauthorized Exception
      throw new LoginRequiredException("인증되지 않은 jwt token입니다.");
    }
  }
}
