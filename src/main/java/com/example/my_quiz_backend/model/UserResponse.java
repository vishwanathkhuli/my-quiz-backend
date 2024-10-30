package com.example.my_quiz_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_responses")
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Each response belongs to a user
    
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;  // Each response belongs to a quiz

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;  // Each response belongs to a question

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;  // Each response may select an answer

}
