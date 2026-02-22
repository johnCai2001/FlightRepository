package com.example.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Entitty.FlightMetaEntity;

public interface FlightMetaRepository extends JpaRepository<FlightMetaEntity, Long> {

  @Query("""
    select fm from FlightMetaEntity fm
    left join fetch fm.airline
    left join fetch fm.aircraftType
    where fm.flightNum in :nums
  """)
  List<FlightMetaEntity> findAllByFlightNumInFetch(@Param("nums") Collection<String> nums);
}