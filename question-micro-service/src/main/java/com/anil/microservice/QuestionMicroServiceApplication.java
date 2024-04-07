package com.anil.microservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching	
public class QuestionMicroServiceApplication {
	private static Logger logger=LogManager.getLogger(QuestionMicroServiceApplication.class);

	public static void main(String[] args) {
		logger.info("Inside Question Micro Service");
		SpringApplication.run(QuestionMicroServiceApplication.class, args);
	}

}
