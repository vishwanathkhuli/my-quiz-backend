package com.example.my_quiz_backend.dto;

import lombok.Data;

@Data
public class ResultResponse {
	private long userId;
	private long quizId;
    private int score;
    private int correctAnswers;
    private int totalQuestions;

    public ResultResponse(Long userId, Long quizId, int score, int correctAnswers, int totalQuestions) {
    	this.userId = userId;
    	this.quizId = quizId;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
    }
}
