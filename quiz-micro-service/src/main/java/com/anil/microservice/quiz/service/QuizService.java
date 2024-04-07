package com.anil.microservice.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anil.microservice.quiz.dao.QuizDAO;
import com.anil.microservice.quiz.feign.QuizFeignInterface;
import com.anil.microservice.quiz.model.QuestionWrapper;
import com.anil.microservice.quiz.model.Quiz;
import com.anil.microservice.quiz.model.Response;

@Service

public class QuizService {
	private static Logger logger=LogManager.getLogger(QuizService.class);

	@Autowired
	public QuizDAO quizrepo;
	
	@Autowired
	QuizFeignInterface quizFeign;
	


	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		

		try {
			// get the question using category and number of question by using Feign interface which connect to question microservice
			List<Integer> questions=quizFeign.getQuestionsForQuiz(category, numQ).getBody();
			
			
			Quiz quiz=new Quiz();
			quiz.setTitle(title);
			quiz.setQuestionIds(questions);
			quizrepo.save(quiz);
			
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		List<Integer> questionIds =quizrepo.findById(id).get().getQuestionIds();
		
		

		
		return new ResponseEntity<>(quizFeign.getQuestions(questionIds).getBody(),HttpStatus.OK);
	}

	
	public ResponseEntity<String> calculateResult(int quizid, List<Response> res) {

		
		return new ResponseEntity<>(quizFeign.getScore(res).getBody(),HttpStatus.OK);
	}


}
