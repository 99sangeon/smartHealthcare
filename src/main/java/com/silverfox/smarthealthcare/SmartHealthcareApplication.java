package com.silverfox.smarthealthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartHealthcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHealthcareApplication.class, args);
	}

}
