package ru.tbank.practicum.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tbank.practicum.dto.BatteryEvent;
import ru.tbank.practicum.dto.WeatherDto;
import ru.tbank.practicum.dto.WeatherResponse;
import ru.tbank.practicum.entity.Batteries;
import ru.tbank.practicum.entity.LoggingBattery;
import ru.tbank.practicum.entity.UsersCoords;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.BatteryLoggingRepository;
import ru.tbank.practicum.repository.BatteryRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;
    private final SettingMapper settingMapper;
    private final BatteryLoggingRepository batteryLoggingRepository;
    private final BatteryRepository batteryRepository;

    @Value("${app.cred.token}")
    private String token;

    @KafkaListener(topics = "topic-weather", groupId = "group1")
    void listener(BatteryEvent batteryEvent) {

        log.info("New BatteryEvent " + batteryEvent);

        Batteries batteries = batteryRepository.findById(batteryEvent.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        LoggingBattery loggingBattery = new LoggingBattery();
        loggingBattery.setBatteries(batteries);
        loggingBattery.setTemp(batteries.getTempNow());
        loggingBattery.setTime(LocalDateTime.now());

        log.info(loggingBattery.toString());

        batteryLoggingRepository.save(loggingBattery);
        batteryRepository.updateTempBattery(batteries.getId(), batteryEvent.getTempSet());

        log.info("update");
    }

    public WeatherDto getWeatherData(UsersCoords usersCoords) {
        WeatherResponse weatherResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("lat", usersCoords.getLat())
                        .queryParam("lon", usersCoords.getLon())
                        .queryParam("units", "metric")
                        .queryParam("appid", token)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();

        return settingMapper.getDto(weatherResponse, usersCoords.getUserId().getId());
    }
}