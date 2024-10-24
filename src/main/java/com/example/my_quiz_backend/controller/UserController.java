package com.example.my_quiz_backend.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.my_quiz_backend.model.User;
import com.example.my_quiz_backend.repository.UserRepository;
import com.example.my_quiz_backend.service.UserService;
import com.example.my_quiz_backend.dto.UserRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
		userService.registerUser(userRequest);
		return ResponseEntity.ok("User Registered Successfully.....");
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        // Get the logged-in user's username from the SecurityContext
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Fetch user details by username
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }
}
