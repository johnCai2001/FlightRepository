package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.FlightService;

@RestController
public class FlightController {

	@GetMapping("/")
	public  String Dash() {
		return "FlightDash";
	}
	@GetMapping("/dashboard")
	public  String Login() {
		return "MainDash";
}
	
}
