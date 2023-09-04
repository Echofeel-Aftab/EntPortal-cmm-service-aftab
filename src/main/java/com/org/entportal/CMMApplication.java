package com.org.entportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.org.entportal")
public class CMMApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMMApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list of allowed origins
        config.addAllowedOriginPattern("*");
        config.addAllowedOriginPattern("/api/**");
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "application/json"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/cmm/cashDepositApproval").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/bank").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/bulk").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/collection").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/deposit").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/user").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/collect/entry").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/depositSlipGeneration").allowedOrigins("http://172.18.62.63:7062");
                registry.addMapping("/cmm/queryPrintDepositSlip").allowedOrigins("http://172.18.62.63:7062");
            }
        };
    }
}
