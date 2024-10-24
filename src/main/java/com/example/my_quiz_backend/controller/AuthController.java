package com.example.my_quiz_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.my_quiz_backend.dto.JwtRequest;
import com.example.my_quiz_backend.dto.JwtResponse;
import com.example.my_quiz_backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
		return new ResponseEntity<>(authService.login(jwtRequest), HttpStatus.OK);
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<JwtResponse> refreshToken(@RequestBody Map<String, String> request) {
	    String refreshToken = request.get("refreshToken");

	    // Call the service method to refresh the access token
	    String newAccessToken = authService.refreshAccessToken(refreshToken);
	    
	    // Return both tokens (new access token and the same refresh token)
	    JwtResponse response = JwtResponse.builder()
	                                      .jwtToken(newAccessToken)
	                                      .refreshToken(refreshToken)
	                                      .build();
	    
	    return ResponseEntity.ok(response);
	}

}
