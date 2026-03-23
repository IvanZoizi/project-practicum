package ru.tbank.practicum.mapper;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.entity.ActionCurtainsEntity;
import ru.tbank.practicum.entity.StatusEntity;
import ru.tbank.practicum.entity.TemperatureEntity;
import ru.tbank.practicum.service.dto.CommonData;
import ru.tbank.practicum.service.dto.Weather;
import ru.tbank.practicum.service.dto.WeatherResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SettingMapper {

    public WeatherDto getDto(WeatherResponse weatherResponse) {
        Double temperature = Optional.ofNullable(weatherResponse)
                .map(WeatherResponse::getMain)
                .map(CommonData::getTemp)
                .orElse(null);

        String weatherMain = Optional.ofNullable(weatherResponse)
                .map(WeatherResponse::getWeather)
                .filter(weatherList -> !weatherList.isEmpty())
                .map(List::getFirst)
                .map(Weather::getMain)
                .orElse(null);

        return new WeatherDto(null, temperature, weatherMain);
    }

    public TemperatureDto getDto(TemperatureEntity temperatureEntity) {
        return new TemperatureDto(
                temperatureEntity.getId(),
                temperatureEntity.getTemperature()
        );
    }

    public StatusWindowBlindDto getDto(StatusEntity statusEntity) {
        return new StatusWindowBlindDto(
                statusEntity.getId(),
                statusEntity.getStatus()
        );
    }

    public ActionCurtainsDto getDto(ActionCurtainsEntity actionCurtainsEntity) {
        return new ActionCurtainsDto(
                actionCurtainsEntity.getId(),
                actionCurtainsEntity.getTime(),
                actionCurtainsEntity.getStatus()
        );
    }
}