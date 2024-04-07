package com.anil.microservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anil.microservice.dao.QuestionDAO;
import com.anil.microservice.model.Question;
import com.anil.microservice.model.QuestionWrapper;
import com.anil.microservice.model.Response;

import jakarta.persistence.Cacheable;


@Service

public class QuestionService {
	private static Logger logger=LogManager.getLogger(QuestionService.class);

	@Autowired
	public QuestionDAO repo;

	public ResponseEntity<List<Question>> getAllQuestions() {
			logger.info("inside get All Questions method");
		try {
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numQ) {
		
		List<Question> questions=repo.getQuestionsByCategory(category, numQ);
		List<Integer> questionIds=new ArrayList<>();
		for(Question q:questions) {
			questionIds.add(q.getId());
		}
		
		return new ResponseEntity<>(questionIds,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionsIds) {
		List<QuestionWrapper> qws=new ArrayList<>();
		for(Integer i:questionsIds) {
			Optional<Question> q=repo.findById(i);
			if(q.isPresent()) {
				QuestionWrapper qw=new QuestionWrapper(q.get().getId(), q.get().getQuestionTitle(), q.get().getOption1(), q.get().getOption2(), q.get().getOption3(), q.get().getOption4());
				qws.add(qw);
			}
			
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<>(qws,HttpStatus.OK);
	}

	
	public ResponseEntity<String> getScore(List<Response> res) {
		Integer result=0;
		for(Response r:res) {
			if(r.getResponse().equals(repo.findById(r.getId()).get().getRightAnswer())) {
				result ++;
			}
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<>(result.toString(),HttpStatus.OK);
	}
}
