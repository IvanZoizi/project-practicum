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
import ru.tbank.practicum.dto.enums.Status;
import ru.tbank.practicum.entity.*;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.BatteryLoggingRepository;
import ru.tbank.practicum.repository.BatteryRepository;
import ru.tbank.practicum.repository.BlindLoggingRepository;
import ru.tbank.practicum.repository.BlindRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;
    private final SettingMapper settingMapper;
    private final BatteryLoggingRepository batteryLoggingRepository;
    private final BatteryRepository batteryRepository;
    private final BlindRepository blindRepository;
    private final BlindLoggingRepository blindLoggingRepository;

    @Value("${app.cred.token}")
    private String token;

    @KafkaListener(topics = "topic-time", groupId = "group1")
    void listenerTime(LocalTime localTime) {

        log.info("Start Blind status by LocalTime: " + localTime + " between " + localTime.plusMinutes(1));

        List<Blinds> blindsList = blindRepository.findAllByTimeCloseBetweenAndStatus(localTime,
                localTime.plusMinutes(1),
                Status.OPENED);
        for (Blinds blind : blindsList) {
            blindRepository.updateStatusBlind(blind.getId(),
                    Status.CLOSED);
            LoggingBlind loggingBlind = new LoggingBlind();
            loggingBlind.setBlinds(blind);
            loggingBlind.setNewStatus(Status.CLOSED);
            loggingBlind.setTime(LocalDateTime.now());
            blindLoggingRepository.save(loggingBlind);
        }

        blindsList = blindRepository.findAllByTimeOpenBetweenAndStatus(localTime,
                localTime.plusMinutes(1),
                Status.CLOSED);
        for (Blinds blind : blindsList) {
            blindRepository.updateStatusBlind(blind.getId(),
                    Status.OPENED);
            LoggingBlind loggingBlind = new LoggingBlind();
            loggingBlind.setBlinds(blind);
            loggingBlind.setNewStatus(Status.OPENED);
            loggingBlind.setTime(LocalDateTime.now());
            blindLoggingRepository.save(loggingBlind);
        }
    }

    @KafkaListener(topics = "topic-weather", groupId = "group1")
    void listenerWeater(BatteryEvent batteryEvent) {

        log.info("New BatteryEvent " + batteryEvent);

        Batteries batteries = batteryRepository.findById(batteryEvent.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        log.info("Start logging battery");

        LoggingBattery loggingBattery = new LoggingBattery();
        loggingBattery.setBatteries(batteries);
        loggingBattery.setTemp(batteries.getTempNow());
        loggingBattery.setTime(LocalDateTime.now());

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