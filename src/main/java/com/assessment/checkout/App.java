package com.assessment.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * Spring Boot main class.
 *
 */
@SpringBootApplication
@Profile("dev") //Using dev profile for POC purpose
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
