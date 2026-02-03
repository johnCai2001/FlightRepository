package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightStateDto {
	
	    public FlightStateDto(String string, double d, double e, int i, int j, int k) {
		// TODO Auto-generated constructor stub
	}

		private String flightNum;
	   
	    private double lat;
	    
	    private double lon;
	    
	    private int heading;
	    
	    private int altitude;
	    
	    private int speed;
		
}
