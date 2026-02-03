package com.example.dto;


public class FlightStateDto {
	
		private String flightNum;
	   
	    private double lat;
	    
	    private double lon;
	    
	    private int heading;
	    
	    private int altitude;
	    
	    private int speed;

		public String getFlightNum() {
			return flightNum;
		}

		public void setFlightNum(String flightNum) {
			this.flightNum = flightNum;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double lon) {
			this.lon = lon;
		}

		public int getHeading() {
			return heading;
		}

		public void setHeading(int heading) {
			this.heading = heading;
		}

		public int getAltitude() {
			return altitude;
		}

		public void setAltitude(int altitude) {
			this.altitude = altitude;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}

		public FlightStateDto(String flightNum, double lat, double lon, int heading, int altitude, int speed) {
			super();
			this.flightNum = flightNum;
			this.lat = lat;
			this.lon = lon;
			this.heading = heading;
			this.altitude = altitude;
			this.speed = speed;
		}
	
	    
	    
}
