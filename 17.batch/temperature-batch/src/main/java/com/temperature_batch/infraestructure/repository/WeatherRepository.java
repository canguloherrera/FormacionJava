package com.temperature_batch.infraestructure.repository;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.domain.WeatherRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {
}
