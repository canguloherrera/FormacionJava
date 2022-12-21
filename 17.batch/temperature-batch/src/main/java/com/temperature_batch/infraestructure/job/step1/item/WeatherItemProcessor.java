package com.temperature_batch.infraestructure.job.step1.item;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.domain.WeatherRisk;
import org.springframework.batch.item.ItemProcessor;

public class WeatherItemProcessor implements ItemProcessor<Weather, Weather> {
    @Override
    public Weather process(Weather weather) throws Exception {

        if (weather.getTemperature() > 50 || weather.getTemperature() < -20) {
            throw new IllegalArgumentException();
        }

        return weather;

    }
}

