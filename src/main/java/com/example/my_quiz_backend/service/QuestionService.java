package com.example.my_quiz_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.my_quiz_backend.model.Question;
import com.example.my_quiz_backend.model.Quiz;
import com.example.my_quiz_backend.repository.QuestionRepository;
import com.example.my_quiz_backend.repository.QuizRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuizRepository quizRepository;

    // Create question and associate with quiz
    public Question createQuestion(Question question, Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
