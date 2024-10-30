package com.example.my_quiz_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.my_quiz_backend.model.UserResponse;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long>{

	@Query("SELECT ur FROM UserResponse ur WHERE ur.user.id = :userId AND ur.question.quiz.id = :quizId")
    List<UserResponse> findByUserIdAndQuizId(@Param("userId") Long userId, @Param("quizId") Long quizId);

}
