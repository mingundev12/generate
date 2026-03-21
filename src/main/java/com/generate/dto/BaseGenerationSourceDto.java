package com.generate.dto;

import com.generate.entity.GenerateSource;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseGenerationSourceDto implements GenerationSourceDto{
    protected long sourceId;
    protected double maxCapacity;
    protected LocalDateTime time;
    protected LocalDateTime sunrise;
    protected LocalDateTime sunset;
    protected double temp;
    protected double gti;

    public BaseGenerationSourceDto(GenerateSource source, WeatherDailyDto dailyDto, WeatherDto weatherDto) {
        this.sourceId = source.getSourceId();
        this.maxCapacity = source.getMaxCapacity();
        this.time = weatherDto.getTime();
        this.sunrise = dailyDto.getSunrise();
        this.sunset = dailyDto.getSunset();
        this.temp = weatherDto.getTemperature();
        this.gti = weatherDto.getGti();
    }

    @Override
    public double getEfficiency() {
        double irradianceRatio = gti / 1000.0;
        double peak_temp = 25.0;

        double temp_factor = Math.max( 1.0 + ( peak_temp - temp ) * 0.004, 0.0 );

        return irradianceRatio * temp_factor;
    }
}
