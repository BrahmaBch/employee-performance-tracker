package com.employee.performance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorld {
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello : Welcome to the Employee Performance Tracker application";
	}

}
