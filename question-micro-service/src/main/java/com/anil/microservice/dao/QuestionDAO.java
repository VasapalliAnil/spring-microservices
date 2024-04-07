package com.anil.microservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anil.microservice.model.Question;



@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	
	

	 @Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	    List<Question> getQuestionsByCategory(@Param("category") String category, @Param("limit") int limit);
	

}
