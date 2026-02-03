package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.FlightStateDto;
import com.example.service.FlightService;
@RestController

@RequestMapping("API/Flight")
public class FlightAPIController {

	@Autowired
	private FlightService  flightService; 
	
	@GetMapping("/Flight/API")
	public  List<FlightStateDto> GetFlight () {
	
		return 	flightService.getFlights();
		    }
	}
