package com.stb.politik.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {

    private static Logger log = LoggerFactory.getLogger(CorsConfig.class.getName());

    //parece que no es necesario teniendo el WebMvcConfigurer
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    //más habitual de Spring MVC
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        log.info("CorsConfig - corsConfigurer() ----------------");
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/login")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("*")
                    .exposedHeaders("*");

                registry
                    .addMapping("/api/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("*")
                    .exposedHeaders("*");
            }
        };
    }
}