package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.FlightService;

@Controller
public class FlightController {

	@Autowired
	private FlightService flightService; 
	
	@GetMapping("/")
	public  String Dash() {
		System.out.println("Login sucess");
		return "FlightDash";
	}
	@GetMapping("/dashboardPage")
	public String Login() {
	    return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
	  List<String> flightNumbers = flightService.getFlightNumbers();
	  model.addAttribute("flightNumbers", flightNumbers);
	  System.out.println("flightNumbers => " + flightNumbers);
	  return "MainDash";
	}


	         }
	

