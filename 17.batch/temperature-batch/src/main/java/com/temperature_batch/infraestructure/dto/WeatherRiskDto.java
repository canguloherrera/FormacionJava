package com.temperature_batch.infraestructure.dto;

import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.dto.WeatherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link WeatherRisk} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRiskDto implements Serializable {
    private  String location;
    private  Integer month;
    private  Integer numberMeasurements;
    private  Integer year;
    private  double AverageTemperature;
    private  String risk;
    private  Date date;
    private  WeatherDto weather;
}