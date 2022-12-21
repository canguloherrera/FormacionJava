package com.temperature_batch.infraestructure.job.step3.mapper;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherRiskRowMapper implements RowMapper<WeatherRiskDto> {
    @Override
    public WeatherRiskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        WeatherRiskDto weatherRisk = new WeatherRiskDto();
        weatherRisk.setLocation(rs.getString("location"));
        weatherRisk.setYear(rs.getInt("year"));
        weatherRisk.setMonth(rs.getInt("month"));
        weatherRisk.setNumberMeasurements(rs.getInt("numberMeasurements"));
        weatherRisk.setAverageTemperature(rs.getDouble("averageTemperature"));
        weatherRisk.setRisk(rs.getString("risk"));
        return weatherRisk;
    }
}
