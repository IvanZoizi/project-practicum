package ru.tbank.practicum.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cglib.core.Local;
import ru.tbank.practicum.controller.dto.BatteryTempDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WindowActionDto;
import ru.tbank.practicum.entity.ActionStatus;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.BatterySettingsRepository;
import ru.tbank.practicum.repository.BatteryTempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tbank.practicum.repository.WindowBlindActionRepository;
import ru.tbank.practicum.repository.WindowBlindSettingsRepository;

import java.awt.*;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class SettingServiceImpl implements SettingService {
    private final SettingMapper settingMapper;
    private final BatterySettingsRepository batterySettingsRepository;
    private final BatteryTempRepository batteryTempRepository;
    private final WindowBlindSettingsRepository windowBlindSettings;
    private final WindowBlindActionRepository windowBlindActionRepository;

    @Override
    public TemperatureDto getTemperature(Long id) {

        MDC.put("id", String.valueOf(id));
        log.info("Get Temperature");

        return settingMapper.getDtoBatterySettings(
                batterySettingsRepository.findById(id)
        );
    }

    @Override
    public BatteryTempDto updateTemp(Long id, Long temp, Long setTemp) {
        MDC.put("id", String.valueOf(id));
        MDC.put("temp", String.valueOf(temp));
        MDC.put("setTemp", String.valueOf(setTemp));

        log.info("Update Temp");
        batteryTempRepository.updateTempById(id, temp, setTemp);
        return settingMapper.getDtoBatteryTemp(
                batteryTempRepository.findById(id)
        );
    }

    public StatusWindowBlindDto getStatusWindowBlind(Long id) {
        MDC.put("id", String.valueOf(id));
        log.info("Get status window blind");
        return settingMapper.getDtoWindowStatus(
                windowBlindSettings.findById(id)
        );
    }

    public WindowActionDto updateWindow(Long id, LocalDateTime timeStart, LocalDateTime timeEnd, ActionStatus status) {
        MDC.put("id", String.valueOf(id));
        MDC.put("timeStart", String.valueOf(timeStart));
        MDC.put("timeEnd", String.valueOf(timeEnd));
        MDC.put("status", String.valueOf(status));
        log.info("updateWindow");
        windowBlindActionRepository.updateWindowById(id, timeStart, timeEnd, status);
        return settingMapper.getDtoWindowAction(
                windowBlindActionRepository.findById(id)
        );
    }
}
