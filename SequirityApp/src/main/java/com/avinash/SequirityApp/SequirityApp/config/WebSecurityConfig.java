package com.avinash.SequirityApp.SequirityApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.
//                authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/posts","/error","/public/**").permitAll()
//                                .requestMatchers("/posts/**").hasAnyRole("USER")
//                                .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());



        httpSecurity.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/posts","/error","/public/**","/auth/**").permitAll()
                        .requestMatchers("/posts/**").hasAnyRole("USER")
                        .anyRequest().authenticated())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();

    }
//check home




//===============================================================================================================================
//    this is only used for testing purpose untill we don,t prepare Service-layer

//    @Bean
//    UserDetailsService myInMemoryUserDetailsService(){
//
//        UserDetails normalUser = User
//                .withUsername("avillllllllnash")
//                .password(passwordEncoder().encode("oikkonlkkomn3"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("alpknnhhm;vi")
//                .password(passwordEncoder().encode("pljnjk,@"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//
//    }

//===========================================================================================================================



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
