package com.temperature_batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@Table(name = "TemperatureRisk")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRisk {
    @Id
    @GeneratedValue

    private Long id;
    private String location;
    private Integer month;
    private Integer numberMeasurements;
    private Integer year;
    private double AverageTemperature;
    private int risk;

    @OneToOne
    private Weather weather;


}
