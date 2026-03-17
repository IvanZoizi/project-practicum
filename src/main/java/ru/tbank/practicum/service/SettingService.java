package ru.tbank.practicum.service;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.service.dto.WeatherResponse;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;

import java.time.LocalTime;

public interface SettingService {

    TemperatureDto getTemperature(long id);
    TemperatureDto newTemperature(int temperature);
    void deleteTemperature(long id);

    StatusWindowBlindDto getStatusWindowBlind(long id);
    StatusWindowBlindDto newStatusWindowBlind(StatusWindowBlindEnum status);
    void deleteStatus(long id);

    ActionCurtainsDto getTimeActionCurtains(long id);
    ActionCurtainsDto newTimeActionCurtains(LocalTime time, ActionCurtainsEnum actionCurtainsEnum);
    void deleteTimeActionCurtains(long id);
}
