package com.project.ecommerce_platform.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/user/signup") // Allow all paths
                        .allowedOrigins("http://localhost:5173/**") // Specify the allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify the allowed methods
                        .allowedHeaders("*") // Specify the allowed headers
                        .allowCredentials(true); // Allow credentials (e.g., cookies)
            }
        };
    }
}
