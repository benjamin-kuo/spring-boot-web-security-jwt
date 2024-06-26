package com.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import com.tutorial.data.LoginRequestVo;
import com.tutorial.data.LoginResponseVo;
import com.tutorial.persistence.entity.UserEntity;
import com.tutorial.service.JwtTokenService;
import com.tutorial.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/secure/")
public class SecureController {

  @Autowired
  UserService userService;

  @Autowired
  private JwtTokenService jwtTokenService;

  @GetMapping("/hello")
  public ResponseEntity<String> hello(){
    return ResponseEntity.ok("hello spring");
  }

  /**
   * expected 200 OK
   * @param user user info
   */
  @PostMapping("/user")
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
    return ResponseEntity.ok(userService.createUser(user));
  }

  /**
   * expected 200 OK
   * @param id id
   */
  @GetMapping("/user/{id}")
  public ResponseEntity<UserEntity> getAccount(@PathVariable Long id){
    return ResponseEntity.ok(userService.getUser(id));
  }

  /**
   * @return expected 403 Forbidden
   */
  @GetMapping("/users")
  public ResponseEntity<List<UserEntity>> getAccounts(){
    return ResponseEntity.ok(userService.getUsers());
  }

  @PostMapping("/auth/login")
  public ResponseEntity<LoginResponseVo> login(@RequestBody LoginRequestVo req) {
    return ResponseEntity.ok(jwtTokenService.createToken(req));
  }

  @GetMapping("/parse-token")
  public ResponseEntity<Claims> parseToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
    //Map<String, Object> jwtPayload = jwtTokenService.parseToken(authorization);
    return ResponseEntity.ok(jwtTokenService.parseToken(authorization));
  }

  @PostMapping("/auth/refresh-token")
  public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> request) {
    String refreshToken = request.get("refreshToken");
    String accessToken = jwtTokenService.refreshAccessToken(refreshToken);
    Map<String, String> res = Map.of("accessToken", accessToken);

    return ResponseEntity.ok(res);
  }
}
