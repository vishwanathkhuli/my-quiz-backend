package com.example.my_quiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    private Long id;
    private String title;
    private String description;
    private Integer totalQuestions;
    private Integer timeLimit;
    private List<QuestionResponse> questions; // Make sure this matches your expected structure
}
