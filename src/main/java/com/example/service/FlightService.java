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

    private final OpenSkyClient openSkyClient;
    
    private  List<FlightStateDto> flights = new ArrayList<>();
    
    private FlightMetaRepository  flightMetaRepo;
	
	@Autowired	
	private  MockFlightProvider mock;

	  public FlightService(MockFlightProvider mock, OpenSkyClient openSkyClient) {
	    this.mock = mock;
	    this.openSkyClient = openSkyClient;
	  }
	  
    // 取得航班所有資訊
	  public List<FlightStateDto> getFlights() {
		   List<FlightStateDto> flights = mock.getFlight();
		    	return flights;
	  }
	  // 取得航班號
    public List<FlightStateDto> getFlightNumbers() {
    // 收集 flightNum
    List<String> nums = flights.stream()
        .map(FlightStateDto::getFlightNum)
        .filter(Objects::nonNull)
        .distinct()
        .collect(Collectors.toList());
    // DB 一次查回來
    List<FlightMetaEntity> metas = flightMetaRepo.findAllByFlightNumInFetch(nums);

    // 做成 Map: flightNum -> meta
    Map<String, FlightMetaEntity> metaMap = new HashMap<>();
    for (FlightMetaEntity m : metas) {
      metaMap.put(m.getFlightNum(), m);
    }

    // 填回 DTO（有查到才填，沒查到就空）
    for (FlightStateDto f : flights) {
      FlightMetaEntity m = metaMap.get(f.getFlightNum());
      if (m == null) continue;

      if (m.getAirline() != null) {
        f.setAirlineName(m.getAirline().getName());
        f.setAirlineIcao(m.getAirline().getIcao());
      }
      if (m.getAircraftType() != null) {
        f.setAircraftType(m.getAircraftType().getIcaoType());
        f.setAircraftModel(m.getAircraftType().getModel());
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
            dto.setFlightNum(m.callsign);
            dto.setLat(m.lat);
            dto.setLon(m.lon);
            dto.setHeading(heading);
            dto.setAltitude(altitude);
            dto.setSpeed(speed);
            dto.setIcao24(m.icao24);
            next.add(dto);
        }
        this.flights=next;
        System.out.println("updateFromOpenSky => " + next.size());
      }
}
