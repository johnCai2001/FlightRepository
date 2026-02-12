package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Provider.MockFlightProvider;
import com.example.dto.FlightStateDto;
import com.example.integration.opensky.OpenSkyModel;


@Service
public class FlightService {
	
	@Autowired	
	private final MockFlightProvider mock;

	  public FlightService(MockFlightProvider mock) {
	    this.mock = mock;
	  }
	  
    // 取得航班所有資訊
    public List<FlightStateDto> getFlights() {
   return  mock.getFlight();
    }
    
    public List<String> getFlightNumbers() {
        return mock.getFlightNumbers();
      }
    
    public void updateFromOpenSky(List<OpenSkyModel> models) {

        List<FlightStateDto> next = new ArrayList<>();

        for (OpenSkyModel m : models) {

            int heading = (int) Math.round(m.heading);
            int altitude = (int) Math.round(m.altitudeFt); // ft
            int speed = (int) Math.round(m.speedKt);       // kt

            next.add(new FlightStateDto(
                m.callsign,   // flightNum
                m.lat,
                m.lon,
                heading,
                altitude,
                speed
            ));
        }
        System.out.println("updateFromOpenSky => " + next.size());
    }
   
}
