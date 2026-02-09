package com.example.integration.opensky;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenSkyMapper {

    @SuppressWarnings("unchecked")
    public static List<OpenSkyModel> map(Map<String, Object> body) {

        List<OpenSkyModel> result = new ArrayList<>();

        if (body == null || body.get("states") == null) {
            return result;
        }

        List<List<Object>> states = (List<List<Object>>) body.get("states");

        for (List<Object> row : states) {

        	String icao24 = row.get(0) != null ? row.get(0).toString().trim() : null;
        	String callsign = row.get(1) != null ? row.get(1).toString().trim() : null;
        	Double lon = row.get(5) instanceof Number ? ((Number) row.get(5)).doubleValue() : null;
        	Double lat = row.get(6) instanceof Number ? ((Number) row.get(6)).doubleValue() : null;
        	Double altM = row.get(7) instanceof Number ? ((Number) row.get(7)).doubleValue() : null;
        	Boolean onGround = row.get(8) instanceof Boolean ? (Boolean) row.get(8) : null;
        	Double velMs = row.get(9) instanceof Number ? ((Number) row.get(9)).doubleValue() : null;
        	Double track = row.get(10) instanceof Number ? ((Number) row.get(10)).doubleValue() : null;

        	if (lat == null || lon == null) continue;
        	if (Boolean.TRUE.equals(onGround)) continue;

        	String flightKey =
        	        (callsign != null && !callsign.isBlank()) ? callsign : icao24;
        	if (flightKey == null || flightKey.isBlank()) continue;

        	OpenSkyModel m = new OpenSkyModel();
        	m.callsign = flightKey;
        	m.lat = lat;
        	m.lon = lon;
        	m.heading = track != null ? track : 0;
        	m.altitudeFt = altM != null ? altM * 3.28084 : 0;
        	m.speedKt = velMs != null ? velMs * 1.94384 : 0;

        	result.add(m);

        }

        return result;
    }
}