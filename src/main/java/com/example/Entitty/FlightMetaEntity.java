package com.example.Entitty;

import jakarta.persistence.*;

@Entity
@Table(name = "flight_meta")
public class FlightMetaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "flight_num", nullable = false, unique = true)
  private String flightNum;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "airline_id")
  private AirlineEntity airline;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "aircraft_type_id")
  private AircraftTypeEntity aircraftType;

  private String note;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getFlightNum() { return flightNum; }
  public void setFlightNum(String flightNum) { this.flightNum = flightNum; }
  public AirlineEntity getAirline() { return airline; }
  public void setAirline(AirlineEntity airline) { this.airline = airline; }
  public AircraftTypeEntity getAircraftType() { return aircraftType; }
  public void setAircraftType(AircraftTypeEntity aircraftType) { this.aircraftType = aircraftType; }
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }
}