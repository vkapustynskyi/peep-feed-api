package com.vkapustynskyi.peepfeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class ApplicationConfig {

    @Bean
    public AuditorAware<String> auditProvider() {
        return new AuditorAwareImpl();
    }

}
