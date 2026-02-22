package com.example.Entitty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "airlines")
public class AirlineEntity {
      @Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String icao;
	  private String iata;

	  @Column(nullable = false)
	  private String name;

	  private String country;

	  @Column(name = "logo_url")
	  private String logoUrl;

	  // getters/setters
	  public Long getId() { return id; }
	  public void setId(Long id) { this.id = id; }
	  public String getIcao() { return icao; }
	  public void setIcao(String icao) { this.icao = icao; }
	  public String getIata() { return iata; }
	  public void setIata(String iata) { this.iata = iata; }
	  public String getName() { return name; }
	  public void setName(String name) { this.name = name; }
	  public String getCountry() { return country; }
	  public void setCountry(String country) { this.country = country; }
	  public String getLogoUrl() { return logoUrl; }
	  public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}
