package com.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tutorial.data.LoginRequestVo;
import com.tutorial.data.LoginResponseVo;
import com.tutorial.service.JwtTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class PublicController {

  @Autowired
  private JwtTokenService jwtTokenService;

  @GetMapping("/hello")
  public ResponseEntity<String> hello(){
    return ResponseEntity.ok("hello public");
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseVo> login(@RequestBody LoginRequestVo req) {
    return ResponseEntity.ok(jwtTokenService.createToken(req));
  }

}
