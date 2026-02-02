package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightStateDto {
	   private double lon;      
	    private int heading;     
	    private int altitude;    
	    private int speed;
		public FlightStateDto() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
