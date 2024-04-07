package com.anil.quizapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuizappApplication {
	/*
	 * add the dependency class
	 */
	private static Logger logger=LogManager.getLogger(QuizappApplication.class);
	public static void main(String[] args) {
		logger.info("info");
		//logger.debug("debug");
		
		SpringApplication.run(QuizappApplication.class, args);
	}

}
