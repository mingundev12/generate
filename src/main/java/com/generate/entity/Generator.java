package com.generate.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Generator {
    private long generatorId;
    private String generatorName;
    private double latitude;
    private double longitude;
    private String locationAddr;
    private boolean isActive;
    private LocalDateTime createdAt;
}
