package org.example.helper.global.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder () { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain securityConfig (HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers("/auth/login", "/user/signup", "/error").permitAll()
                        .anyRequest().authenticated();
                })

                .sessionManagement(session
                    -> { session.sessionCreationPolicy(SessionCreationPolicy.STATELESS); }
                )

                .headers(header
                    -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )

                .build();
    }
}
