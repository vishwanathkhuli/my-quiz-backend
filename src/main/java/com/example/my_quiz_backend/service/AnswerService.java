package com.example.my_quiz_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.my_quiz_backend.model.Answer;
import com.example.my_quiz_backend.model.Question;
import com.example.my_quiz_backend.repository.AnswerRepository;
import com.example.my_quiz_backend.repository.QuestionRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Save answers for a question
    public void saveAnswers(List<Answer> answers, Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));
        
        for (Answer answer : answers) {
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
    }
}
