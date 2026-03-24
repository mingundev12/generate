package com.generate.dto;

import com.generate.entity.GenerateSource;

public class DummyGenerationSourceDto extends BaseGenerationSourceDto{
    @Override
    public double getEfficiency() {
        double noise = 0.98 + ( Math.random() * 0.04 );

        return super.getEfficiency() * noise;
    }

    public DummyGenerationSourceDto(GenerateSource source, WeatherDailyDto dailyDto, WeatherDto weatherDto) {
        super(source, dailyDto, weatherDto);
    }
}
