package ru.tbank.practicum.service;

import ru.tbank.practicum.controller.dto.*;
import ru.tbank.practicum.entity.ActionStatus;
import ru.tbank.practicum.service.dto.WeatherResponse;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface SettingService {

    TemperatureDto getTemperature(Long id);
    BatteryTempDto updateTemp(Long id, Long temp, Long setTemp);

    StatusWindowBlindDto getStatusWindowBlind(Long id);
    WindowActionDto updateWindow(Long id, LocalDateTime timeStart, LocalDateTime timeEnd, ActionStatus status);
}
