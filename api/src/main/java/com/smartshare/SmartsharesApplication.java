package com.smartshare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
@EnableConfigurationProperties(ApplicationProperties.class)
@Slf4j
public class SmartsharesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartsharesApplication.class, args);
	}

	@Bean
	CorsFilter corsFilter(ApplicationProperties properties) {
		CorsConfiguration cors = properties.getCors();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		if (cors.getAllowedOrigins() != null && !cors.getAllowedOrigins().isEmpty()) {
			source.registerCorsConfiguration("/api/**", cors);
		}
		return new CorsFilter(source);
	}



}
