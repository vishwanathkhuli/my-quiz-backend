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
public class QuestionRequest {

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotBlank(message = "Question type is required")
    private String questionType; // e.g., MULTIPLE_CHOICE, TRUE_FALSE

    @Size(min = 2, message = "At least two answers are required")
    @NotNull(message = "Answers are required")
    private List<AnswerRequest> answers;

    private String difficulty; // e.g., EASY, MEDIUM, HARD
}


