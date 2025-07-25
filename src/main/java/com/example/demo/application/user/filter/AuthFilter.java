package com.example.demo.application.user.filter;

import com.example.demo.application.user.helper.TokenHelper;
import com.example.demo.platform.core.handler.ResponseHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter {
  private final TokenHelper tokenHelper;
  private final ApplicationContext context;
  private final ResponseHandler responseHandler;

  public AuthFilter(
      TokenHelper tokenHelper, ApplicationContext context, ResponseHandler responseHandler) {
    this.tokenHelper = tokenHelper;
    this.context = context;
    this.responseHandler = responseHandler;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = authHeader.substring(7);
      String userId = tokenHelper.validateToken(token);

      if (SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetailsService userDetailsService = context.getBean(UserDetailsService.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception e) {
      responseHandler.handleUnauthorizedResponse(response);
    }

    filterChain.doFilter(request, response);
  }
}
