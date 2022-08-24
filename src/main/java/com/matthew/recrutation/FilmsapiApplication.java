package com.matthew.recrutation;

import com.matthew.recrutation.dto.user.RegisterNewUserRequestDTO;
import com.matthew.recrutation.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
public class FilmsapiApplication {

    private final UserManagementService userManagementService;

    public static void main(String[] args) {
        SpringApplication.run(FilmsapiApplication.class, args);
    }

    @Bean
    @EventListener(FilmsapiApplication.class)
    public void setupAccounts() {
        userManagementService.registerNewUser(new RegisterNewUserRequestDTO("mati1", "mati1"));
        userManagementService.registerNewUser(new RegisterNewUserRequestDTO("mati2", "mati2"));
        userManagementService.registerNewUser(new RegisterNewUserRequestDTO("mati3", "mati3"));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("**");
            }
        };
    }

}
