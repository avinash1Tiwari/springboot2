package com.avinash.SequirityApp.SequirityApp.config;

import com.avinash.SequirityApp.SequirityApp.filters.JwtAuthFilter;
import com.avinash.SequirityApp.SequirityApp.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.avinash.SequirityApp.SequirityApp.enums.Role.ADMIN;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;


    private static final String[] publicRoutes = {
        "/error","/public/**","/auth/**","/home.html"
    };

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
                        .requestMatchers(publicRoutes).permitAll()
//                        .requestMatchers("/posts/**").hasAnyRole(ADMIN.name())
                        .requestMatchers( HttpMethod.GET,"/posts/**").permitAll()                   //// to permit getPost request to all users
                        .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyRole(ADMIN.name())     //// post(create post) method is only allowedto user            ////
                        .anyRequest().authenticated())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)     ///// custom filter
                .oauth2Login(oauth2Config -> oauth2Config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );     ////for googleAuth login functionality

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
