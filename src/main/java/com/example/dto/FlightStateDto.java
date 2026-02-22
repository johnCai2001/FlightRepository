package com.example.dto;


public class FlightStateDto {
	
		private String flightNum;
	    private double lat;
	    private double lon;
	    private int heading;
	    private int altitude;
	    private int speed;
	    private String departurecity; 
	    private String arrivalcity;
	    private String departureAirport; 
	    private String arrivalAirport; 
	    private String icao24;
	    private String airlineName;     // 航空公司名稱
	    private String airlineIcao;     // 航空公司 ICAO (例如 EVA)
	    private String aircraftType;    // 例如 A320
	    private String aircraftModel;   // 例如 A320-200

		public FlightStateDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public FlightStateDto(String flightNum, double lat, double lon, int heading, int altitude, int speed,
				String departurecity, String arrivalcity, String departureAirport, String arrivalAirport,
				String icao24) {
			super();
			this.flightNum = flightNum;
			this.lat = lat;
			this.lon = lon;
			this.heading = heading;
			this.altitude = altitude;
			this.speed = speed;
			this.departurecity = departurecity;
			this.arrivalcity = arrivalcity;
			this.departureAirport = departureAirport;
			this.arrivalAirport = arrivalAirport;
			this.icao24 = icao24;
		}

		public String getDeparturecity() {
			return departurecity;
		}

		public void setDeparturecity(String departurecity) {
			this.departurecity = departurecity;
		}

		public String getIcao24() {
			return icao24;
		}

		public void setIcao24(String icao24) {
			this.icao24 = icao24;
		}

		public String getDepaturecity() {
			return departurecity;
		}

		public void setDepaturecity(String depaturecity) {
			this.departurecity = depaturecity;
		}

		public String getArrivalcity() {
			return arrivalcity;
		}

		public void setArrivalcity(String arrivalcity) {
			this.arrivalcity = arrivalcity;
		}

		public String getDepartureAirport() {
			return departureAirport;
		}

		public void setDepartureAirport(String departureAirport) {
			this.departureAirport = departureAirport;
		}

		public String getArrivalAirport() {
			return arrivalAirport;
		}

		public void setArrivalAirport(String arrivalAirport) {
			this.arrivalAirport = arrivalAirport;
		}

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

		public FlightStateDto(String flightNum,
                double lat,
                double lon,
                int heading,
                int altitude,
                int speed,
                String departureAirport,
                String arrivalAirport,
                String departureCity,
                String arrivalCity) {

				this.flightNum = flightNum;
				this.lat = lat;
				this.lon = lon;
				this.heading = heading;
				this.altitude = altitude;
				this.speed = speed;
				this.departureAirport = departureAirport;
				this.arrivalAirport = arrivalAirport;
				this.departurecity = departurecity;
				this.arrivalcity = arrivalcity;
				}

	    
	    
}
