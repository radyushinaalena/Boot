package ru.skypro.lessons.springboot.weblibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/*"))
                .authorizeHttpRequests(this::customizeRequest)
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return http.build();
    }

    private void customizeRequest(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        try {
            registry.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/**")).hasAnyRole("USER");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}