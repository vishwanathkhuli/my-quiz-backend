package com.example.my_quiz_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequest {

    @NotBlank(message = "Quiz title is required")
    @Size(min = 3, max = 100, message = "Quiz title must be between 3 and 100 characters")
    private String title;

    private String description;

    @NotNull(message = "Total number of questions is required")
    private Integer totalQuestions;

    @NotNull(message = "Time limit is required")
    private Integer timeLimit; // in minutes

    @NotNull(message = "Questions are required")
    @Size(min = 1, message = "At least one question is required")
    private List<QuestionRequest> questions;

}

