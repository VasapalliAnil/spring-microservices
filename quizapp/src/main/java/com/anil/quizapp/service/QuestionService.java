package com.anil.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anil.quizapp.dao.QuestionDAO;
import com.anil.quizapp.model.Question;


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
}
