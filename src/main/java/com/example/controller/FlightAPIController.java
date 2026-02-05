package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.FlightStateDto;
import com.example.service.FlightService;

@RestController
@RequestMapping("/api/flight")

public class FlightAPIController {

	@Autowired
	private FlightService flightService; 
	
	@GetMapping
	public  List<FlightStateDto> GetFlightData () {
		System.out.println("GetFlightData");
		return 	flightService.getFlights();
		    }
	
	
	}
