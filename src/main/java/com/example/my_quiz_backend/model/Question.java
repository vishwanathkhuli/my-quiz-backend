package com.example.my_quiz_backend.model;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Use only specified fields
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Include id in hashCode and equals
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonIgnoreProperties("questions") // Ignore the quiz reference to prevent circular reference
    private Quiz quiz;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String questionType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column
    private String difficulty;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("question") // Ignore the question reference in answers
    private Set<Answer> answers;
}

