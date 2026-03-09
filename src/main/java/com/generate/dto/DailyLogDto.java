package com.generate.dto;

import com.generate.entity.GenerationLog;
import com.generate.type.StatusCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DailyLogDto {
    private long logId;
    private double generationKwh;
    private double efficiency;
    private StatusCode statusCode;
    private LocalDateTime createdAt;

    public DailyLogDto(GenerationLog generationLog) {
        this.logId = generationLog.getLogId();
        this.generationKwh = generationLog.getGenerationKwh();
        this.efficiency = generationLog.getEfficiency();
        this.statusCode = generationLog.getStatusCode();
        this.createdAt = generationLog.getCreatedAt();
    }
}
