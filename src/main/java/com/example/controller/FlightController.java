package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightController {

	@GetMapping("/")
	public  String Dash() {
		System.out.println("Login sucess");
		return "FlightDash";
	}
	@GetMapping("/dashboard")
	public  String Login() {
		return "MainDash";
}
	
}
