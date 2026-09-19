package com.shaltout.jobportal.config;

import com.shaltout.jobportal.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final String[] publicUrl={
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**","/favicon.ico","/resources/**","/error"
    };

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers(publicUrl).permitAll();
                    auth.anyRequest().authenticated();
                }
        );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
