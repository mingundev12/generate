package com.generate.dto;

import java.time.LocalDateTime;

public interface GenerationSourceDto {
    long getSourceId();
    double getMaxCapacity();
    LocalDateTime getTime();
    LocalDateTime getSunrise();
    LocalDateTime getSunset();
    double getTemp();
    double getGti();
    double getEfficiency();
}
