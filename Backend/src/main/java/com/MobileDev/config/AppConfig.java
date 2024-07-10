package com.MobileDev.config;

import com.MobileDev.security.PasswordEncoder;
import com.MobileDev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Configuration
    public static class JwtConfig{
        @Value("${jwt.secret}")
        private String secretKey;

        @Bean
        public String jwtSecretKey() {
            return secretKey;
        }
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder.encoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
