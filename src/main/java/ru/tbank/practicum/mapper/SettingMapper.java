package ru.tbank.practicum.mapper;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.entity.ActionCurtainsEntity;
import ru.tbank.practicum.entity.StatusEntity;
import ru.tbank.practicum.entity.TemperatureEntity;
import ru.tbank.practicum.service.dto.WeatherResponse;
import org.springframework.stereotype.Component;

@Component
public class SettingMapper {

    public WeatherDto getDtoFromWeatherResponse(WeatherResponse weatherResponse) {
        return new WeatherDto(
            null,
            weatherResponse.getMain().getTemp(),
            weatherResponse.getWeather().getFirst().getMain()
        );
    }

    public TemperatureDto getDtoFromEntityTemperature(TemperatureEntity temperatureEntity) {
        return new TemperatureDto(
                temperatureEntity.id,
                temperatureEntity.temperature
        );
    }

    public StatusWindowBlindDto getDtoFromEntityStatus(StatusEntity statusEntity) {
        return new StatusWindowBlindDto(
                statusEntity.id,
                statusEntity.status
        );
    }

    public ActionCurtainsDto getDtoFromEntityAction(ActionCurtainsEntity actionCurtainsEntity) {
        return new ActionCurtainsDto(
                actionCurtainsEntity.id,
                actionCurtainsEntity.time,
                actionCurtainsEntity.status
        );
    }
}
