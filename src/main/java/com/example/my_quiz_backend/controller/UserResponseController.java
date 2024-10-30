package com.example.my_quiz_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.my_quiz_backend.dto.ResultResponse;
import com.example.my_quiz_backend.dto.UserResponseRequest;
import com.example.my_quiz_backend.service.UserResponseService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/responses")
public class UserResponseController {

    @Autowired
    private UserResponseService userResponseService;

    // Submit user response to a question
    @PostMapping("/submit")
    public ResponseEntity<ResultResponse> submitResponse(@Valid @RequestBody UserResponseRequest userResponseRequest) {
        ResultResponse resultResponse = userResponseService.submitUserResponse(userResponseRequest);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }
}

