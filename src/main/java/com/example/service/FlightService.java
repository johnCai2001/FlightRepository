package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entitty.FlightMetaEntity;
import com.example.Provider.MockFlightProvider;
import com.example.Repository.FlightMetaRepository;
import com.example.dto.FlightStateDto;
import com.example.integration.opensky.OpenSkyClient;
import com.example.integration.opensky.OpenSkyModel;


@Service
public class FlightService {

    private List<FlightStateDto> flights = new ArrayList<>();

    @Autowired
    private MockFlightProvider mock;

    @Autowired
    private FlightMetaRepository flightMetaRepo;

    public List<FlightStateDto> getFlights() {
    	  return flights;
    	}

    public List<String> getFlightNumbers() {
    	  return flights.stream()
    	      .map(FlightStateDto::getFlightNum)
    	      .filter(Objects::nonNull)
    	      .map(String::trim)
    	      .filter(s -> !s.isEmpty())
    	      .distinct()
    	      .collect(Collectors.toList());
    	}

    public List<FlightStateDto> getFlightsWithMeta() {

        // 1) 收集 flightNum
        List<String> nums = flights.stream()
                .map(FlightStateDto::getFlightNum)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        if (nums.isEmpty()) return flights;

        // 2) DB 一次查回來
        List<FlightMetaEntity> metas = flightMetaRepo.findAllByFlightNumInFetch(nums);

        // 3) flightNum -> meta
        Map<String, FlightMetaEntity> metaMap = new HashMap<>();
        for (FlightMetaEntity m : metas) {
            if (m.getFlightNum() != null) {
                metaMap.put(m.getFlightNum().trim(), m);
            }
        }

        for (FlightStateDto f : flights) {
            if (f.getFlightNum() == null) continue;

            FlightMetaEntity m = metaMap.get(f.getFlightNum().trim());
            if (m == null) continue;

            //fake airline
            if (m.getAirline() != null) {
               f.setAirlineName("China Airline");
            }
            //fake aircraft
            if (m.getAircraftType() != null) {
                f.setAircraftType(m.getAircraftType().getIcaoType());
                f.setAircraftType("B777-300ER");
            }
        }
        return flights;
    }

    public void updateFromOpenSky(List<OpenSkyModel> models) {

        List<FlightStateDto> next = new ArrayList<>();

        for (OpenSkyModel m : models) {

            int heading = (int) Math.round(m.heading);
            int altitude = (int) Math.round(m.altitudeFt);
            int speed = (int) Math.round(m.speedKt);

            FlightStateDto dto = new FlightStateDto();
            dto.setFlightNum(m.callsign == null ? null : m.callsign.trim());
            dto.setLat(m.lat);
            dto.setLon(m.lon);
            dto.setHeading(heading);
            dto.setAltitude(altitude);
            dto.setSpeed(speed);
            dto.setIcao24(m.icao24);
            dto.setAircraftType("B777-300ER");
            dto.setAirlineName("China Airline");

            next.add(dto);
        }

        this.flights = next;
        System.out.println("updateFromOpenSky => " + next.size());
    }
}