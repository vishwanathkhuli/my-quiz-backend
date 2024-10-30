package com.example.my_quiz_backend.model;

import jakarta.persistence.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "quizzes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Use only specified fields
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Include id in hashCode and equals
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "total_questions", nullable = false)
    private int totalQuestions;

    @Column(name = "time_limit", nullable = false)
    private int timeLimit;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("quiz") // Ignore the quiz reference in questions
    private Set<Question> questions;
}

