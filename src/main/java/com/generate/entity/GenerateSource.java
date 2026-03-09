package com.generate.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class GenerateSource {
    private long sourceId;
    private long generatorId;
    private String sourceSn;
    private LocalDate installedAt;
    private double maxCapacity;
    private LocalDateTime createdAt;
}
