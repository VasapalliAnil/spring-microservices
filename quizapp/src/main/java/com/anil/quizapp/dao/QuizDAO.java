package com.anil.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anil.quizapp.model.Quiz;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
