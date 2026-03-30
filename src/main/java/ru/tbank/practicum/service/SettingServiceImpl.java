package ru.tbank.practicum.service;

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
        return settingMapper.getDtoBatterySettings(
                batterySettingsRepository.findById(id)
        );
    }

    @Override
    public BatteryTempDto updateTemp(Long id, Long temp, Long setTemp) {
        System.out.println(id);
        System.out.println(temp);
        System.out.println(setTemp);
        batteryTempRepository.updateTempById(id, temp, setTemp);
        return settingMapper.getDtoBatteryTemp(
                batteryTempRepository.findById(id)
        );
    }

    public StatusWindowBlindDto getStatusWindowBlind(Long id) {
        return settingMapper.getDtoWindowStatus(
                windowBlindSettings.findById(id)
        );
    }

    public WindowActionDto updateWindow(Long id, LocalDateTime timeStart, LocalDateTime timeEnd, ActionStatus status) {
        windowBlindActionRepository.updateWindowById(id, timeStart, timeEnd, status);
        return settingMapper.getDtoWindowAction(
                windowBlindActionRepository.findById(id)
        );
    }
}
