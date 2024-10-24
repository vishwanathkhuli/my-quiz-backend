package com.example.my_quiz_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.my_quiz_backend.dto.ResultRequest;
import com.example.my_quiz_backend.dto.ResultResponse;
import com.example.my_quiz_backend.model.Quiz;
import com.example.my_quiz_backend.model.Result;
import com.example.my_quiz_backend.model.User;
import com.example.my_quiz_backend.repository.QuizRepository;
import com.example.my_quiz_backend.repository.ResultRepository;
import com.example.my_quiz_backend.repository.UserRepository;
import com.example.my_quiz_backend.repository.UserResponseRepository;

@Service
public class ResultService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private ResultRepository resultRepository;


    public ResultResponse getResultByUserQuizId(ResultRequest resultRequest) {
        // Retrieve the user by ID
        User user = userRepository.findById(resultRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve the quiz by ID
        Quiz quiz = quizRepository.findById(resultRequest.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Retrieve the result for the given user and quiz
        Result result = resultRepository.findByUserAndQuiz(user, quiz)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        // Create and return the ResultResponse
        return new ResultResponse(user.getId(), quiz.getId(), result.getScore(), result.getCorrectAnswers(), result.getTotalQuestions());
    }

}

