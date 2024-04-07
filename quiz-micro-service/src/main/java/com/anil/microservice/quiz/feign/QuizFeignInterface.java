package com.anil.microservice.quiz.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anil.microservice.quiz.model.QuestionWrapper;
import com.anil.microservice.quiz.model.Response;

@FeignClient("QUESTION-MICRO-SERVICE")

public interface QuizFeignInterface {
	
	
	// generate
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam int numQ) ;
	// getQuestions(questionId)
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsIds);
	
	// getScore
	@PostMapping("question/getScore")
	public ResponseEntity<String> getScore(@RequestBody List<Response> res);
	
	

}
