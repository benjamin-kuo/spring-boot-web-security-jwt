package com.tutorial.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Order(1)
public class ReqRespFilterComponent implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    log.info("Global Filter Request {} : {}", req.getMethod(), req.getRequestURI());
    filterChain.doFilter(servletRequest, servletResponse);
    log.info("Global Filter Response :{}", res.getContentType());

  }
}
