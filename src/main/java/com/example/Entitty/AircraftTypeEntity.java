package com.example.Entitty;
import jakarta.persistence.*;

@Entity
@Table(name = "aircraft_types")
public class AircraftTypeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "icao_type")
  private String icaoType;

  private String manufacturer;
  private String model;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getIcaoType() { return icaoType; }
  public void setIcaoType(String icaoType) { this.icaoType = icaoType; }
  public String getManufacturer() { return manufacturer; }
  public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }
}