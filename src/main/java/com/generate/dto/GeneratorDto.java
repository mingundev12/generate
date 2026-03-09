package com.generate.dto;

import com.generate.entity.Generator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GeneratorDto {
    private long generatorId;
    private String generatorName;
    private LocalDateTime createdAt;

    public GeneratorDto(Generator generator) {
        this.generatorId = generator.getGeneratorId();
        this.generatorName = generator.getGeneratorName();
        this.createdAt = generator.getCreatedAt();
    }
}
