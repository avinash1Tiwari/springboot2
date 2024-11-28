package com.avinash.SequirityApp.SequirityApp.config;

import com.avinash.SequirityApp.SequirityApp.auth.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.modelmapper.ModelMapper;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareIpml")
public class AppConfig {

    @Bean
    ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditorAwareIpml()
    {
        return new AuditorAwareImpl();
    }
}
