package com.avinash.SequirityApp.SequirityApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                authorizeHttpRequests(auth -> auth
                                .requestMatchers("/posts").permitAll()
//                                .requestMatchers("/posts/**").hasAnyRole("ADMIN","USER")
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());


        return httpSecurity.build();

    }

}
