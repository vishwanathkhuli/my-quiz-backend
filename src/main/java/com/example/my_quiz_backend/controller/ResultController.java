package com.example.my_quiz_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_quiz_backend.dto.ResultRequest;
import com.example.my_quiz_backend.dto.ResultResponse;
import com.example.my_quiz_backend.service.ResultService;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;
    
    @GetMapping("/get")
    public ResponseEntity<?> getResultByQuizUserId(@RequestBody ResultRequest resultRequest){
    	ResultResponse resultResponse = resultService.getResultByUserQuizId(resultRequest);
    	return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }
	
}

