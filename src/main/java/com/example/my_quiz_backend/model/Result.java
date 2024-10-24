package com.example.my_quiz_backend.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Integer score;

	@Column(name = "attempted_at", nullable = false)
	private LocalDateTime attemptedAt = LocalDateTime.now();

	@Column
	private Integer totalQuestions;

	@Column
	private Integer correctAnswers;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;  // Each result belongs to a user

	@ManyToOne
	@JoinColumn(name = "quiz_id", nullable = false)
	private Quiz quiz;  // Each result belongs to a quiz
}
