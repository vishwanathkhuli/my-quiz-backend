package com.example.my_quiz_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.my_quiz_backend.dto.UserRequest;
import com.example.my_quiz_backend.exception.UserAlreadyExistsException;
import com.example.my_quiz_backend.model.User;
import com.example.my_quiz_backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void registerUser(UserRequest userRequest) {
		if (userRepository.existsByUsername(userRequest.getUsername())) {
			throw new UserAlreadyExistsException("Username is already taken!");
		}

		if (userRepository.existsByEmail(userRequest.getEmail())) {
			throw new UserAlreadyExistsException("Email is already registered!");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

		User user = new User();
		user.setEmail(userRequest.getEmail());
		user.setPassword(encodedPassword);
		user.setUsername(userRequest.getUsername());
		userRepository.save(user);
	}

	public User getUser(long id) {
		return userRepository.findById(id).get();
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
