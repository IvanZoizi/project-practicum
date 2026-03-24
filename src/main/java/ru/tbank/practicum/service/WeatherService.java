package ru.tbank.practicum.service;

import ru.tbank.practicum.controller.dto.WeatherDto;

public interface WeatherService {
    WeatherDto getWeatherData(double lat, double lon);
}
