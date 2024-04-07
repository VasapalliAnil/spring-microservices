package com.anil.microservice.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMicroServiceApplication.class, args);
	}

}
