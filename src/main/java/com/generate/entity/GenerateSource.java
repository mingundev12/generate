package com.generate.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class GenerateSource {
    private long sourceId;
    private long generatorId;
    private String sourceSn;
    private Date installedAt;
    private double maxCapacity;
    private LocalDateTime createdAt;
}
