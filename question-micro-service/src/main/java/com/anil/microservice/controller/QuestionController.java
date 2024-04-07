package com.anil.microservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.microservice.model.Question;
import com.anil.microservice.model.QuestionWrapper;
import com.anil.microservice.model.Response;
import com.anil.microservice.service.QuestionService;



@RestController
@RequestMapping("question")
public class QuestionController {
	private static Logger logger=LogManager.getLogger(QuestionController.class);
	
	@Autowired
	public QuestionService questionService;
	
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions(){
		logger.info("in all questions method ");
		return questionService.getAllQuestions();
		
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	// generate
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam int numQ) {
		return questionService.getQuestionsForQuiz(category,numQ);
	}
	// getQuestions(questionId)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsIds){
		return questionService.getQuestions(questionsIds);
	}
	
	// getScore
	@PostMapping("getScore")
	public ResponseEntity<String> getScore(@RequestBody List<Response> res){
		return questionService.getScore(res);
		
	}
	
	
	
}
