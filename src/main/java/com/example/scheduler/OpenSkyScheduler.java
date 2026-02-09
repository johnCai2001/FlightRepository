package com.example.scheduler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.integration.opensky.OpenSkyClient;
import com.example.integration.opensky.OpenSkyMapper;
import com.example.integration.opensky.OpenSkyModel;
import com.example.service.FlightService;

@Component
public class OpenSkyScheduler {

    private final OpenSkyClient openSkyClient;
    private final FlightService flightService;

    @Value("${bbox.lamin}") private double lamin;
    @Value("${bbox.lamax}") private double lamax;
    @Value("${bbox.lomin}") private double lomin;
    @Value("${bbox.lomax}") private double lomax;

    public OpenSkyScheduler(OpenSkyClient openSkyClient, FlightService flightService) {
        this.openSkyClient = openSkyClient;
        this.flightService = flightService;
    }

    @Scheduled(fixedRate = 5000)
    public void refreshOpenSky() {
        Map<String, Object> body = openSkyClient.fetchStates(lamin, lamax, lomin, lomax);
        List<OpenSkyModel> models = OpenSkyMapper.map(body);

        // 把 OpenSky 最新資料塞進你原本的 FlightService
        flightService.updateFromOpenSky(models);

        System.out.println("OpenSky states => " + models.size());
    }
}

