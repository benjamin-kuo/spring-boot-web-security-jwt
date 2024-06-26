package com.tutorial.config;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.tutorial.filter.JwtAuthFilter;
import com.tutorial.service.JwtTokenService;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * 設定規則
   * @param http HttpSecurity
   * @return SecurityFilterChain
   * @throws Exception exception
   */
  // @Bean
  // public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter authFilter) throws Exception {
  //   http.authorizeHttpRequests(registry ->
  //       registry.requestMatchers(HttpMethod.POST, "/user").permitAll()         //POST, 可新增帳號
  //           .requestMatchers(HttpMethod.GET, "/user/?*").permitAll()           //GET, 可查詢單一帳號
  //           //.requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
  //           .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()       //GET, 可查詢單一帳號
  //           //.anyRequest().permitAll()                                        //不控
  //           .anyRequest().authenticated()                                      //其它全數拒絕
  //   ).csrf(AbstractHttpConfigurer::disable).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
  //   return http.build();
  // }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter authFilter) throws Exception {
    http.authorizeHttpRequests(registry ->
        registry.requestMatchers(HttpMethod.POST, "/public/**").permitAll()     //POST
            .requestMatchers(HttpMethod.GET, "/public/**").permitAll()          //GET
            .anyRequest().authenticated()                                       //其它全數拒絕
    ).csrf(AbstractHttpConfigurer::disable).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }


  /**
   * 加密採用 spring 本身的 BCryptPasswordEncoder
   * @return PasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    var provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;
  }

  /**
   * JWT Token Bean
   * @param key secret key
   * @param accessTokenTtl  access token 的存活時間
   * @param refreshTokenTtl refresh token  的存活時間
   * @param authenticationProvider 驗證提供者
   */
  @Bean
  public JwtTokenService jwtTokenService(@Value("${jwt.secret-key}") String key, @Value("${jwt.access-ttl}") int accessTokenTtl,
      @Value("${jwt.refresh-ttl}") int refreshTokenTtl, AuthenticationProvider authenticationProvider) {
    SecretKey jwtSecretKey = Keys.hmacShaKeyFor(key.getBytes());
    JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(jwtSecretKey).build();
    return new JwtTokenService(jwtSecretKey, jwtParser, accessTokenTtl, refreshTokenTtl, authenticationProvider);
  }
}
