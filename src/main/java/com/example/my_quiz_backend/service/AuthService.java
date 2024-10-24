package com.example.my_quiz_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.my_quiz_backend.dto.JwtRequest;
import com.example.my_quiz_backend.dto.JwtResponse;
import com.example.my_quiz_backend.jwt.JwtAuthenticationHelper;

@Service
public class AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtAuthenticationHelper jwtAuthenticationHelper;

	@Autowired
	UserDetailsService userDetailsService;

	public JwtResponse login(JwtRequest jwtRequest) {
		// Authenticate user and generate tokens
		this.doAuthentication(jwtRequest.getUsername(), jwtRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

		// Generate access token
		String accessToken = jwtAuthenticationHelper.generateToken(userDetails);

		// Generate refresh token (you can generate it in the same way as the access
		// token)
		String refreshToken = jwtAuthenticationHelper.generateRefreshToken(userDetails);

		// Return the tokens in the response
		return JwtResponse.builder().jwtToken(accessToken).refreshToken(refreshToken) // Set the refresh token here
				.build();
	}

	private void doAuthentication(String username, String password) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}

	public String refreshAccessToken(String refreshToken) {
		// Check if the refresh token is valid
		if (jwtAuthenticationHelper.isTokenValid(refreshToken)) {
			// Get the username from the refresh token
			String username = jwtAuthenticationHelper.getUsernameFromToken(refreshToken);

			// Load user details based on the username
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			// Generate a new access token
			return jwtAuthenticationHelper.generateToken(userDetails); // New Access Token
		} else {
			throw new BadCredentialsException("Invalid Refresh Token");
		}
	}

}
