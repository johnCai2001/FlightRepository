package com.example.dto;

public class RouteDto {

    public String departureAirport;
    public String arrivalAirport;
    public String departureCity;
    public String arrivalCity;
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
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public RouteDto(String departureAirport, String arrivalAirport, String departureCity, String arrivalCity) {
		super();
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
	}
	public RouteDto() {}
}
