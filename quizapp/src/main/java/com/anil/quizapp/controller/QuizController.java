package com.anil.quizapp.controller;

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

import com.anil.quizapp.model.QuestionWrapper;
import com.anil.quizapp.model.Response;
import com.anil.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	public QuizService quizService;
	
	
	
	@PostMapping("create")
	@CrossOrigin
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ , @RequestParam String title){
		
		return quizService.createQuiz(category,numQ,title);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
		return quizService.getQuiz(id);
		
	}
	
	@PostMapping("submit/{quizid}")
	public ResponseEntity<String> calculateResult(@PathVariable int quizid,@RequestBody List<Response> res){
		return quizService.calculateResult(quizid,res);
		
	}
	
	
}
