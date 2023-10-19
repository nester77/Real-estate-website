package com.nester.Rew.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/admins/**").hasAuthority("ADMIN")
                        .requestMatchers("/users/personal", "/users/update", "/users/delete").authenticated()
                        .requestMatchers("/apartments/create/**", "/apartments/update/**", "/apartments/personal").authenticated()
                        .requestMatchers("/**", "/api/v1.0/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll().defaultSuccessUrl("/home"))
                .logout(logout -> logout.logoutSuccessUrl("/home"))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
