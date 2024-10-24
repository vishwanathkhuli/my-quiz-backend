package com.example.my_quiz_backend.service;

import com.example.my_quiz_backend.dto.AnswerResponse;
import com.example.my_quiz_backend.dto.QuestionResponse;
import com.example.my_quiz_backend.dto.QuizRequest;
import com.example.my_quiz_backend.dto.QuizResponse;
import com.example.my_quiz_backend.model.Quiz;
import com.example.my_quiz_backend.model.Question;
import com.example.my_quiz_backend.model.Answer;
import com.example.my_quiz_backend.repository.QuizRepository;
import com.example.my_quiz_backend.repository.QuestionRepository;
import com.example.my_quiz_backend.repository.AnswerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Quiz createQuiz(QuizRequest quizRequest) {
        // Create a new Quiz entity
        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequest.getTitle());
        quiz.setDescription(quizRequest.getDescription());
        quiz.setTotalQuestions(quizRequest.getTotalQuestions());
        quiz.setTimeLimit(quizRequest.getTimeLimit());

        // Save quiz first to generate ID
        quizRepository.save(quiz);

        // Add questions and answers to the quiz
        for (var questionRequest : quizRequest.getQuestions()) {
            Question question = new Question();
            question.setQuiz(quiz);
            question.setQuestionText(questionRequest.getQuestionText());
            question.setQuestionType(questionRequest.getQuestionType());
            question.setDifficulty(questionRequest.getDifficulty());

            // Set createdAt to the current time
            question.setCreatedAt(LocalDateTime.now());

            // Save question to generate ID
            questionRepository.save(question);

            // Add answers to the question
            for (var answerRequest : questionRequest.getAnswers()) {
                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setAnswerText(answerRequest.getAnswerText());
                answer.setIsCorrect(answerRequest.getIsCorrect());

                // Save the answer
                answerRepository.save(answer);
            }
        }
        return quiz;
    }



    public QuizResponse getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id " + quizId));

        // Fetch the questions for the quiz
        List<Question> questions = questionRepository.findByQuizId(quizId);
        List<QuestionResponse> questionResponses = questions.stream()
                .map(this::convertToQuestionResponse)
                .collect(Collectors.toList());

        return new QuizResponse(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getTotalQuestions(),
                quiz.getTimeLimit(),
                questionResponses
        );
    }



    public QuestionResponse convertToQuestionResponse(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setQuestionText(question.getQuestionText());
        questionResponse.setQuestionType(question.getQuestionType());
        questionResponse.setDifficulty(question.getDifficulty());

        // Map answers to DTOs
        List<AnswerResponse> options = question.getAnswers().stream()
                .map(answer -> {
                    AnswerResponse answerResponse = new AnswerResponse();
                    answerResponse.setId(answer.getId());
                    answerResponse.setAnswerText(answer.getAnswerText());
                    answerResponse.setIsCorrect(answer.getIsCorrect()); // Include this if needed
                    return answerResponse;
                })
                .collect(Collectors.toList());

        questionResponse.setOptions(options); // Set the options in the question response

        return questionResponse;
    }
}
