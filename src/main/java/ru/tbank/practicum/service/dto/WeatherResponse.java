package ru.tbank.practicum.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private List<Weather> weather;

    @JsonProperty("main")
    private CommonData main;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("rain")
    private Rain rain;

    @JsonProperty("clouds")
    private Clouds clouds;
}
