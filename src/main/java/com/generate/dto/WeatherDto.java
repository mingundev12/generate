package com.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    private LocalDateTime time;
    @JsonProperty("temperature_2m")
    private double temperature;
    @JsonProperty("global_tilted_irradiance")
    private double gti;
}
