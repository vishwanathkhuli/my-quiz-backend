package com.example.my_quiz_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.my_quiz_backend.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
