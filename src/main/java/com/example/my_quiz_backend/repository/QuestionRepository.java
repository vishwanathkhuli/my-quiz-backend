package com.example.my_quiz_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.my_quiz_backend.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	List<Question> findByQuizId(Long quizId);

	@Query("SELECT q FROM Question q JOIN FETCH q.answers WHERE q.quiz.id = :quizId")
	List<Question> findQuestionsWithAnswersByQuizId(@Param("quizId") Long quizId);
}
