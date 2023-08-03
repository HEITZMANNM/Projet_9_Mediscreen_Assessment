package com.Mediscreen.AppAssessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class AppAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppAssessmentApplication.class, args);
	}

}
