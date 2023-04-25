package com.vkapustynskyi.peepfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing(auditorAwareRef = "auditProvider")
@EnableJpaRepositories
@SpringBootApplication
public class PeepFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeepFeedApplication.class, args);
	}

}
