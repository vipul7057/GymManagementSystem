package com.gym.S3Fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class S3FitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3FitnessApplication.class, args);
	}

}
