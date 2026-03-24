package com.generate.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GenerateResultDto {
    private long sourceId;
    private double maxCapacity;
    private LocalDateTime time;
    private double generatedPower;
    private double expectedPower;
    private double perfomanceRatio;
}
