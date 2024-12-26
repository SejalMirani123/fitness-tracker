package org.fitnesstracker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig
{

    @Bean
    public SecurityFilterChain apiSecurityFilterChain( HttpSecurity httpSecurity ) throws Exception
    {
        return httpSecurity
                .securityMatcher( new OrRequestMatcher(
                        new AntPathRequestMatcher( "/v1/users/**" ),
                        new AntPathRequestMatcher( "/v1/workout/**" ),
                        new AntPathRequestMatcher( "/v1/activity/**" ) ) )
                .csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests( auth -> auth.anyRequest().authenticated() )
                .sessionManagement( session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration )
            throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
