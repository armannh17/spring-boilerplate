package com.example.demo.platform.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.application.user.filter.AuthFilter;

@Configuration
@EnableMethodSecurity()
public class SecurityConfig {
	private final AuthFilter authFilter;

	public SecurityConfig(AuthFilter authFilter) {
		this.authFilter = authFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}
