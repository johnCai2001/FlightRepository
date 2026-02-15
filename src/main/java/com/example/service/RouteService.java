package com.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.RouteDto;

@Service
public class RouteService {

    private final RestTemplate restTemplate = new RestTemplate();

    public RouteDto getRoute(String icao24) {

        long now = System.currentTimeMillis() / 1000;
        long begin = now - 48 * 3600; // 查 48 小時內
        long end = now;

        String url = "https://opensky-network.org/api/flights/aircraft"
                + "?icao24=" + icao24
                + "&begin=" + begin
                + "&end=" + end;

        ResponseEntity<OpenSkyFlight[]> response =
                restTemplate.getForEntity(url, OpenSkyFlight[].class);

        OpenSkyFlight[] flights = response.getBody();
        if (flights == null || flights.length == 0) return null;

        OpenSkyFlight latest = flights[flights.length - 1];

        RouteDto dto = new RouteDto();
        dto.departureAirport = latest.getEstDepartureAirport();
        dto.arrivalAirport = latest.getEstArrivalAirport();

        return dto;
    }
}

