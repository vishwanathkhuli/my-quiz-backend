package com.example.my_quiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String questionText;
    private String questionType;
    private String difficulty;
    private List<AnswerResponse> options; // Name this field accordingly
}
