package ru.tbank.practicum.service;

import org.springframework.stereotype.Service;
import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.SettingRepository;
import ru.tbank.practicum.service.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService{

    private final WebClient webClient;
    private final SettingMapper settingMapper;

    @Value("${app.cred.token}")
    private String token;

    @Override
    public WeatherDto getWeatherData(double lat, double lon) {
        WeatherResponse weatherResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("units", "metric")
                        .queryParam("appid", token)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();

        return settingMapper.getDto(weatherResponse);
    }
}
