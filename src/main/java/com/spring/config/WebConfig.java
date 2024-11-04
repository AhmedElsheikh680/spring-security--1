package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    String [] ALLOWED_ORIGIN = {"http://localhost:4200", "https://goole.com"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(ALLOWED_ORIGIN) // "*"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
