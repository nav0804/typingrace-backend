package com.typingrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TypingRaceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypingRaceBackendApplication.class, args);
	}

}
