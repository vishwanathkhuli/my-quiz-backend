package com.example.my_quiz_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.my_quiz_backend.model.Quiz;
import com.example.my_quiz_backend.model.Result;
import com.example.my_quiz_backend.model.User;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
	
	Optional<Result> findByUserAndQuiz(User user, Quiz quiz);
	
}
