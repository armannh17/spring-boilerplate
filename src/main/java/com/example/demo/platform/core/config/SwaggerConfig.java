package com.example.demo.platform.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("My Demo API").version("1.0").description("API documentation for demo project"))
				.components(new Components().addSecuritySchemes("Bearer Authentication",
						new SecurityScheme().name("Bearer Authentication").type(SecurityScheme.Type.HTTP)
								.scheme("bearer").bearerFormat("JWT")));
	}
}
