package com.vkapustynskyi.peepfeed;

import org.hibernate.envers.strategy.internal.ValidityAuditStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaAuditing(auditorAwareRef = "auditProvider")
@EnableJpaRepositories
@SpringBootApplication
@EnableWebSecurity
public class PeepFeedApplication {

    public static void main(String[] args) {
        System.setProperty("org.hibernate.envers.audit_strategy", ValidityAuditStrategy.class.getName());
        System.setProperty("org.hibernate.envers.audit_strategy_validity_store_revend_timestamp", "true");
        System.setProperty("org.hibernate.envers.store_data_at_delete", "true");
        System.setProperty("org.hibernate.envers.revision_on_collection_change", "false");
        SpringApplication.run(PeepFeedApplication.class, args);
    }

}
