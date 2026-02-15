package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Provider.MockFlightProvider;
import com.example.dto.FlightStateDto;
import com.example.integration.opensky.OpenSkyClient;
import com.example.integration.opensky.OpenSkyModel;


@Service
public class FlightService {

    private final OpenSkyClient openSkyClient;
    
    private volatile List<FlightStateDto> flights = new ArrayList<>();
	
	@Autowired	
	private  MockFlightProvider mock;

	  public FlightService(MockFlightProvider mock, OpenSkyClient openSkyClient) {
	    this.mock = mock;
	    this.openSkyClient = openSkyClient;
	  }
	  
    // 取得航班所有資訊
    public List<FlightStateDto> getFlights() {
   return flights;
    }
    
    public List<String> getFlightNumbers() {
        return mock.getFlightNumbers();
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
