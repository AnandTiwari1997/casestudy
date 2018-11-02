package com.casestudy.rms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Configuration class for allowing Cross-Origin access.
 * 
 * @author Anand Tiwari */
@Configuration
@EnableWebMvc
public class WebCORSFilter implements WebMvcConfigurer {
    
    public static final int TIME = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("content-type", "Authorization",
                "content-length", "access-control-allow-origin", "access-control-allow-headers", "access-control-allow-credentials")
                .allowCredentials(true).exposedHeaders("access-control-allow-origin").maxAge(TIME);
    }
}
