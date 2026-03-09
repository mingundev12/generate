package com.generate.dto;

import com.generate.entity.GenerateSource;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SourceDto {
    private long sourceId;
    private String sourceSn;
    private LocalDate installedAt;
    private double maxCapacity;
    private LocalDateTime createdAt;

    public SourceDto(GenerateSource source) {
        this.sourceId = source.getSourceId();
        this.sourceSn = source.getSourceSn();
        this.installedAt = source.getInstalledAt();
        this.maxCapacity = source.getMaxCapacity();
        this.createdAt = source.getCreatedAt();
    }
}
