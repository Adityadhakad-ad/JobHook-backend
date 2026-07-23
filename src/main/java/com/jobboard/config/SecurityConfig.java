package com.jobboard.config;

import java.util.List;

import org.springframework.http.HttpMethod;
import com.jobboard.security.JwtFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

public SecurityConfig(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

        // Public APIs

        .requestMatchers("/auth/**").permitAll()

        .requestMatchers(HttpMethod.GET, "/jobs/**").permitAll()

        // Admin Only APIs

        .requestMatchers(HttpMethod.POST, "/jobs/**").authenticated()

        .requestMatchers(HttpMethod.PUT, "/jobs/**").authenticated()

        .requestMatchers(HttpMethod.DELETE, "/jobs/**").authenticated()

        .anyRequest().authenticated()
)

            .addFilterBefore(jwtFilter,
                    UsernamePasswordAuthenticationFilter.class)

            .formLogin(form -> form.disable());

    return http.build();
}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}