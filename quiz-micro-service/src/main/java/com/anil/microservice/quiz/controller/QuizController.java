package com.anil.microservice.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.microservice.quiz.model.QuestionWrapper;
import com.anil.microservice.quiz.model.QuizDto;
import com.anil.microservice.quiz.model.Response;
import com.anil.microservice.quiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	public QuizService quizService;
	
	
	
	@PostMapping("create")
	@CrossOrigin
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto){
		
		return quizService.createQuiz(quizdto.getCategoryName(),quizdto.getNumQuestions(),quizdto.getTitle());
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
		
		return quizService.getQuiz(id);
		
	}
	
	@PostMapping("submit/{quizid}")
	public ResponseEntity<String> calculateResult(@PathVariable int quizid,@RequestBody List<Response> res){
		return quizService.calculateResult(quizid,res);
		
	}
	
	
}
