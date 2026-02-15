package com.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.OpenSkyFlight;
import com.example.dto.RouteDto;

@Service
public class RouteService {

    private final RestTemplate restTemplate = new RestTemplate();

    public RouteDto getRoute(String icao24) {
        if (icao24 == null || icao24.isBlank()) return null;

        long now = System.currentTimeMillis() / 1000;
        long begin = now - 48 * 3600;
        long end = now;

        String url = "https://opensky-network.org/api/flights/aircraft"
                + "?icao24=" + icao24
                + "&begin=" + begin
                + "&end=" + end;

        ResponseEntity<OpenSkyFlight[]> response =
                restTemplate.getForEntity(url, OpenSkyFlight[].class);

        OpenSkyFlight[] flights = response.getBody();
        if (flights == null || flights.length == 0) return null;

        // 從後面找第一筆起降都有值的（比 flights[flights.length-1] 穩）
        OpenSkyFlight latest = null;
        for (int i = flights.length - 1; i >= 0; i--) {
            OpenSkyFlight f = flights[i];
            if (f.getEstDepartureAirport() != null && f.getEstArrivalAirport() != null) {
                latest = f;
                break;
            }
        }
        if (latest == null) return null;

        RouteDto dto = new RouteDto();
        dto.departureAirport = latest.getEstDepartureAirport();
        dto.arrivalAirport = latest.getEstArrivalAirport();
        return dto;
    }
}

