package com.example.my_quiz_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class UserResponseRequest {
    private Long userId;
    private Long quizId;
    private List<Response> responses = new ArrayList<>(); // Initialize to prevent null

    @Data
    public static class Response {
        private Long questionId;
        private Long selectedAnswerId;
    }
}


