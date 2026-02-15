package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.FlightStateDto;
import com.example.dto.RouteDto;
import com.example.service.FlightService;
import com.example.service.RouteService;

@RestController
@RequestMapping("/api/flight")

public class FlightAPIController {

	@Autowired
	private FlightService flightService; 
	 
	@Autowired
	private RouteService routeservice;
	
	@GetMapping
	public  List<FlightStateDto> GetFlightData () {
		System.out.println("GetFlightData");
		return 	flightService.getFlights();
		    }
	@GetMapping("/route")
    public  RouteDto route(@RequestParam String icao24) {
		return routeservice.getRoute(icao24);
       }
	}
