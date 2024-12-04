package com.avinash.SequirityApp.SequirityApp.config;

import com.avinash.SequirityApp.SequirityApp.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

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
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

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


//remove passwordEncoder bean from here in order to remove circular dependency and add them into AppConfig
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
