package com.example.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.dto.FlightStateDto;

@Component
public class MockFlightProvider {

    private  List<FlightStateDto> flights = new ArrayList<>();
    private final Random random = new Random();

    private static final double EARTH_RADIUS_M = 6_371_000.0;

    private static final double KNOT_TO_MPS = 0.514444;

    public  List<FlightStateDto>  getFlight() {
        flights.add(new FlightStateDto("AB123", 25.0777, 121.2328, 90, 32000, 450));  // heading=90 東
        flights.add(new FlightStateDto("CD456", 25.12,   121.18,   270, 28000, 430));  // heading=270 西
    return  flights ;
    }
   
    public List<String> getFlightNumbers() {
    	    
    	return flights.stream().map(FlightStateDto::getFlightNum).toList();
    }
    
    @Scheduled(fixedRate = 1000)
    public void track() {
        //
    	final double dtSec = 1.0;

        for (FlightStateDto f : flights) {

            //  讓 heading 有一點點漂移（更像真實飛行）
            //    範圍：-3 ~ +3 度
            int newHeading = (int) Math.round(f.getHeading() + (random.nextDouble() - 0.5) * 6.0);
            newHeading = (newHeading % 360 + 360) % 360; 
            f.setHeading(newHeading);

            //  速度（knots）→ m/s
            double speedMps = f.getSpeed() * KNOT_TO_MPS;

            //  本次前進距離（公尺）
            double distanceM = speedMps * dtSec;

            //  把 heading 轉成弧度（0=北, 90=東, 180=南, 270=西）
            double headingRad = Math.toRadians(f.getHeading());

            //  位移拆成 北向 / 東向（公尺）
            double dNorthM = distanceM * Math.cos(headingRad);
            double dEastM  = distanceM * Math.sin(headingRad);

            //  公尺 → 緯度度數
            double dLatDeg = (dNorthM / EARTH_RADIUS_M) * (180.0 / Math.PI);

            // 公尺 → 經度度數
            double latRad = Math.toRadians(f.getLat());
            double dLonDeg = (dEastM / (EARTH_RADIUS_M * Math.cos(latRad))) * (180.0 / Math.PI);

            // 更新位置
            f.setLat(f.getLat() + dLatDeg);
            f.setLon(f.getLon() + dLonDeg);
        }
    }

   }
            
