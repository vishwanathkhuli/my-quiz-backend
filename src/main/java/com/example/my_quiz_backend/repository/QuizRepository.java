package com.example.my_quiz_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.my_quiz_backend.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
	@Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.id = ?1")
    Optional<Quiz> findQuizWithQuestions(Long quizId);
	
}
