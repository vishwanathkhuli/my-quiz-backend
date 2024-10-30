package com.example.my_quiz_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

    @NotBlank(message = "Answer text is required")
    private String answerText;

    @NotNull(message = "Correct answer flag is required")
    private Boolean isCorrect; // True if the answer is correct
}
