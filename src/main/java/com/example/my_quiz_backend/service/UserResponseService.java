package com.example.my_quiz_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.my_quiz_backend.dto.ResultResponse;
import com.example.my_quiz_backend.dto.UserResponseRequest;
import com.example.my_quiz_backend.model.Answer;
import com.example.my_quiz_backend.model.Question;
import com.example.my_quiz_backend.model.Quiz;
import com.example.my_quiz_backend.model.Result;
import com.example.my_quiz_backend.model.User;
import com.example.my_quiz_backend.model.UserResponse;
import com.example.my_quiz_backend.repository.AnswerRepository;
import com.example.my_quiz_backend.repository.QuestionRepository;
import com.example.my_quiz_backend.repository.QuizRepository;
import com.example.my_quiz_backend.repository.ResultRepository;
import com.example.my_quiz_backend.repository.UserRepository;
import com.example.my_quiz_backend.repository.UserResponseRepository;

@Service
public class UserResponseService {

	@Autowired
	private UserResponseRepository userResponseRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResultRepository resultRepository;

	public ResultResponse submitUserResponse(UserResponseRequest request) {
		List<UserResponse> userResponses = new ArrayList<>();
		int correctAnswers = 0; // To count correct answers

		// Check if responses list is empty
		if (request.getResponses() == null || request.getResponses().isEmpty()) {
			throw new RuntimeException("No questions attempted.");
		}

		// Fetch the quiz to set in user responses
		Quiz quiz = quizRepository.findById(request.getQuizId())
				.orElseThrow(() -> new RuntimeException("Quiz not found"));

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		for (UserResponseRequest.Response response : request.getResponses()) {
			// Find the user, question, and selected answer
			Question question = questionRepository.findById(response.getQuestionId())
					.orElseThrow(() -> new RuntimeException("Question not found"));
			Answer answer = answerRepository.findById(response.getSelectedAnswerId())
					.orElseThrow(() -> new RuntimeException("Answer not found"));

			// Check if the selected answer is correct
			if (answer.getIsCorrect()) {
				correctAnswers++; // Increment correct answers count
			}

			// Create and save the UserResponse
			UserResponse userResponse = new UserResponse();
			userResponse.setUser(user);
			userResponse.setQuestion(question);
			userResponse.setAnswer(answer);
			userResponse.setQuiz(quiz); // Set the quiz for the user response
			userResponses.add(userResponse);
		}

		// Save all user responses in one go
		userResponseRepository.saveAll(userResponses);

		// Calculate the score
		int totalQuestions = request.getResponses().size();
		int score = (int) ((correctAnswers / (double) totalQuestions) * 100); // Calculate percentage score

		// Create and save the Result entity
		Result result = new Result();
		result.setUser(user);
		result.setScore(score);
		result.setCorrectAnswers(correctAnswers);
		result.setTotalQuestions(totalQuestions);
		result.setUser(
				userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
		result.setQuiz(quiz);

		// Save the result to the database
		resultRepository.save(result);

		// Create and return the ResultResponse
		return new ResultResponse(user.getId(), quiz.getId(), score, correctAnswers, totalQuestions);
	}

}
