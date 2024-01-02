package com.sparta.givemetuna.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
	info = @Info(title = "참치사조 업무관리시스템 API DOCS",
		description = "참치사조 업무관리시스템 API 명세서입니다.",
		version = "v1"))
@SecurityScheme(
	name = "Bearer Authentication",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.components(new Components());
	}
//	@Bean
//	public OpenAPI openAPI() {
//		SecurityScheme securityScheme = new SecurityScheme()
//			.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//			.in(SecurityScheme.In.HEADER).name("Authorization");
//
//		SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
//		return new OpenAPI()
//			.components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
//			.security(Arrays.asList(securityRequirement));
//	}
}
