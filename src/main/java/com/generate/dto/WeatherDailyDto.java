package com.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDailyDto {
    private LocalDate time;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    List<WeatherDto> weatherDtoList;
}
