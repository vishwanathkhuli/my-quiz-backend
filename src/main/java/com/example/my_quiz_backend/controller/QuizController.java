package com.example.my_quiz_backend.controller;

import com.example.my_quiz_backend.dto.CreateQuizResponse;
import com.example.my_quiz_backend.dto.QuizRequest;
import com.example.my_quiz_backend.dto.QuizResponse;
import com.example.my_quiz_backend.model.Quiz;
import com.example.my_quiz_backend.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<CreateQuizResponse<Quiz>> createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        Quiz createdQuiz = quizService.createQuiz(quizRequest);
        CreateQuizResponse<Quiz> response = new CreateQuizResponse<Quiz>("Quiz created successfully!", createdQuiz);
        return ResponseEntity.ok(response);
    }
    
 // Get quiz by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        QuizResponse quizResponse = quizService.getQuizById(id);
        return new ResponseEntity<>(quizResponse, HttpStatus.OK);
    }
}

