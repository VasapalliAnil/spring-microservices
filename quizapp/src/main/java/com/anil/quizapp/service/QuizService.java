package com.anil.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anil.quizapp.dao.QuestionDAO;
import com.anil.quizapp.dao.QuizDAO;
import com.anil.quizapp.model.Question;
import com.anil.quizapp.model.QuestionWrapper;
import com.anil.quizapp.model.Quiz;
import com.anil.quizapp.model.Response;

import jakarta.persistence.Cacheable;

@Service

public class QuizService {
	private static Logger logger=LogManager.getLogger(QuizService.class);

	@Autowired
	public QuizDAO quizrepo;
	
	@Autowired
	public QuestionDAO questionrepo;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub

		try {
			// get the question using category and number of question
			List<Question> questions=questionrepo.getQuestionsByCategory(category,numQ);
			
			
			Quiz quiz=new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			quizrepo.save(quiz);
			
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz=quizrepo.findById(id);
		List<QuestionWrapper> qws=new ArrayList<>();
		if(quiz.isPresent()) {
			for(Question q:quiz.get().getQuestions() ) {
				QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
				qws.add(qw);
				
			}
			return new ResponseEntity<>(qws,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	
	public ResponseEntity<String> calculateResult(int quizid, List<Response> res) {
		Optional<Quiz> q=quizrepo.findById(quizid);
		
		Integer result=0;
		if(q.isPresent()) {
			List<Question> qws=q.get().getQuestions();
			for(int i=0;i<res.size();i++) {
				if(res.get(i).getResponse().equals(qws.get(i).getRightAnswer())) {
					result++;
				}
			}
			return new ResponseEntity<>(result.toString(),HttpStatus.OK);
		
		}
		
		return new ResponseEntity<>("No Quiz Found",HttpStatus.BAD_REQUEST);
	}

}
