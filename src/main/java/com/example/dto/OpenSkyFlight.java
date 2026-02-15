package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenSkyFlight {

    @JsonProperty("icao24")
    private String icao24;

    @JsonProperty("firstSeen")
    private long firstSeen;

    @JsonProperty("lastSeen")
    private long lastSeen;

    // OpenSky flights/aircraft 回來的欄位名稱就是這兩個
    @JsonProperty("estDepartureAirport")
    private String estDepartureAirport;

    @JsonProperty("estArrivalAirport")
    private String estArrivalAirport;

    public String getIcao24() { return icao24; }
    public void setIcao24(String icao24) { this.icao24 = icao24; }

    public long getFirstSeen() { return firstSeen; }
    public void setFirstSeen(long firstSeen) { this.firstSeen = firstSeen; }

    public long getLastSeen() { return lastSeen; }
    public void setLastSeen(long lastSeen) { this.lastSeen = lastSeen; }

    public String getEstDepartureAirport() { return estDepartureAirport; }
    public void setEstDepartureAirport(String estDepartureAirport) { this.estDepartureAirport = estDepartureAirport; }

    public String getEstArrivalAirport() { return estArrivalAirport; }
    public void setEstArrivalAirport(String estArrivalAirport) { this.estArrivalAirport = estArrivalAirport; }
}

