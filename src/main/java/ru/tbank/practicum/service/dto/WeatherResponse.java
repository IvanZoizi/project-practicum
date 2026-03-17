package ru.tbank.practicum.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private Coord coord;
    private List<Weather> weather;
    private CommonData main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
}
