package com.generate.entity;

import com.generate.type.StatusCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GenerationLog {
    private long logId;
    private long sourceId;
    private double generationKwh;
    private double efficiency;
    private StatusCode statusCode;
    private LocalDateTime createdAt;
}
