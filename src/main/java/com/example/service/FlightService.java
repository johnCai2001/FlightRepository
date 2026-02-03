package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.FlightStateDto;

@Service
public class FlightService {

    public List<FlightStateDto> getFlights() {
        List<FlightStateDto> flights = new ArrayList<>();
        flights.add(new FlightStateDto("AB123", 25.0777, 121.2328, 90, 32000, 450));
        flights.add(new FlightStateDto("CD456", 25.12,   121.18,   270, 28000, 430));
        return flights;
    }
}

