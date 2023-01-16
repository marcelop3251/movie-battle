package com.api.battle.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class Security {

    @Bean
    public SecurityWebFilterChain springFilter(
      ServerHttpSecurity http
    ) {

        http.csrf().disable()
                .authorizeExchange((authorize) -> authorize
                    .pathMatchers(HttpMethod.GET, "/healthy").permitAll()
                    //.pathMatchers(HttpMethod.POST, "/quizes").permitAll()
                .anyExchange().authenticated()
//                    .and().addFilterBefore(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                ).httpBasic(Customizer.withDefaults())
        ;
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        var manager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationWebFilter authentication(
//      ReactiveAuthenticationManager reactiveAuthenticationManager
//    ) {
//        var authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager);
//        return authenticationWebFilter;
//    }
}
