package com.tutorial.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import com.tutorial.data.LoginRequestVo;
import com.tutorial.data.LoginResponseVo;
import com.tutorial.data.UserDetailVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenService {

  public static final String USER_KEY = "id";
  private final Key secretKey;
  private final JwtParser jwtParser;
  private final int accessTokenTtlSeconds;
  private final int refreshTokenTtlSeconds;
  private final AuthenticationProvider authenticationProvider;

  public JwtTokenService(Key jwtSecretKey, JwtParser jwtParser, int accessTokenTtlSeconds, int refreshTokenTtlSeconds,
      AuthenticationProvider authenticationProvider) {
    secretKey = jwtSecretKey;
    this.jwtParser = jwtParser;
    this.accessTokenTtlSeconds = accessTokenTtlSeconds;
    this.refreshTokenTtlSeconds = refreshTokenTtlSeconds;
    this.authenticationProvider = authenticationProvider;
  }

  public LoginResponseVo createToken(LoginRequestVo request) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
    authToken = authenticationProvider.authenticate(authToken);

    UserDetailVo userDetails = (UserDetailVo) authToken.getPrincipal();

    String accessToken = createAccessToken(userDetails.getUsername());
    String refreshToken = createRefreshToken(userDetails.getUsername());

    LoginResponseVo res = new LoginResponseVo();
    res.setAccessToken(accessToken);
    res.setRefreshToken(refreshToken);
    res.setUserName(userDetails.getUsername());
    res.setUserAuthority(userDetails.getUserAuthority());

    return res;
  }

  public String refreshAccessToken(String refreshToken) {
    Map<String, Object> payload = parseToken(refreshToken);
    String username = (String) payload.get(USER_KEY);

    return createAccessToken(username);
  }

  private String createAccessToken(String userName) {
    return genToken("Access Token", userName, accessTokenTtlSeconds);
  }

  private String createRefreshToken(String userName) {
    return genToken("Refresh Token", userName, refreshTokenTtlSeconds);
  }

  public Claims parseToken(String token) {
    Claims claims = jwtParser.parseClaimsJws(token).getBody();
    log.info("claims:{}", claims);
    return claims;
  }

  private String genToken(String sub, String userName, int tokenDuration) {
    long expirationMillis = Instant.now().plusSeconds(tokenDuration).getEpochSecond() * 1000;
    Claims claims = Jwts.claims();
    claims.setSubject(sub);
    claims.setIssuedAt(new Date());
    claims.setExpiration(new Date(expirationMillis));
    claims.put(USER_KEY, userName);
    return  Jwts.builder().setClaims(claims).signWith(secretKey).compact();
  }

}
