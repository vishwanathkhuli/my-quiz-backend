package com.example.my_quiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
@Builder
public class JwtResponse {

	private String jwtToken;
	
	private String refreshToken;
	
}
