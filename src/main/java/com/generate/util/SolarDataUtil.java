package com.generate.util;

import com.generate.dto.GenerateResultDto;
import com.generate.dto.GenerationSourceDto;

import java.time.LocalDateTime;

public class SolarDataUtil {

    public static double generatePower(LocalDateTime dateTime, double maxCapacity, double efficiency) {
        double hour = dateTime.getHour() + (dateTime.getMinute() / 60.0);
        return generatePower(hour, maxCapacity, efficiency);
    }

    public static double generatePower(double hour, double maxCapacity, double efficiency) {
        double sunriseHrDefault = 6.0;
        double sunsetHrDefault = 19.0;
        return generatePower
                (hour, maxCapacity, efficiency, sunriseHrDefault, sunsetHrDefault);
    }

    public static double generatePower
            (double hour, double maxCapacity, double efficiency,
             double sunriseHr, double sunsetHr) {
        if(hour < sunriseHr || hour > sunsetHr) {
            return 0.0;
        }

        double basePower = Math.sin(Math.PI * (hour - sunriseHr) / (sunsetHr - sunriseHr));
        basePower = Math.max(0, basePower);

        return basePower * maxCapacity * efficiency;
    }

    public static double getEfficiency() {
        // 85% ~ 100%
        return 1 - (Math.random() * 0.15);
    }

    private static double getHourDouble(LocalDateTime dateTime) {
        return dateTime.getHour() + (dateTime.getMinute() / 60.0);
    }

    public static GenerateResultDto generatePower(GenerationSourceDto sourceDto) {
        GenerateResultDto resultDto = new GenerateResultDto();
        resultDto.setSourceId(sourceDto.getSourceId());
        resultDto.setMaxCapacity(sourceDto.getMaxCapacity());

        double sunriseHr = getHourDouble(sourceDto.getSunrise());
        double sunsetHr = getHourDouble(sourceDto.getSunset());
        double timeHr = getHourDouble(sourceDto.getTime());
        double solarHeightRatio = Math.max(0, Math.sin(Math.PI * (timeHr - sunriseHr) / (sunsetHr - sunriseHr)));
        double solarHeightFactor = Math.pow(solarHeightRatio, 3.0);

        resultDto.setExpectedPower(sourceDto.getMaxCapacity() * solarHeightFactor);
        resultDto.setGeneratedPower(sourceDto.getMaxCapacity() * sourceDto.getEfficiency());

        double perfomanceRatio = 1.0;
        if(solarHeightFactor > 0) {
            perfomanceRatio = resultDto.getGeneratedPower() / resultDto.getExpectedPower();
        }

        resultDto.setPerfomanceRatio(perfomanceRatio);

        return resultDto;
    }
}
