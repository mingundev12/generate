package com.generate.util;

import java.time.LocalDateTime;

public class SolarDataUtil {

    private SolarDataUtil() {}

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
}
