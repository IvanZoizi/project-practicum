package ru.tbank.practicum.service;

import ru.tbank.practicum.controller.dto.ActionCurtainsDto;
import ru.tbank.practicum.controller.dto.StatusWindowBlindDto;
import ru.tbank.practicum.controller.dto.TemperatureDto;
import ru.tbank.practicum.controller.dto.WeatherDto;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.SettingRepository;
import ru.tbank.practicum.service.dto.WeatherResponse;
import ru.tbank.practicum.service.enums.ActionCurtainsEnum;
import ru.tbank.practicum.service.enums.StatusWindowBlindEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class SettingServiceImpl implements SettingService {
    private final SettingMapper settingMapper;
    private final SettingRepository settingRepository;

    @Override
    public TemperatureDto getTemperature(long id) {
        return settingMapper.getDto(
                settingRepository.getTemperatureEntity(id)
        );
    }

    @Override
    public TemperatureDto newTemperature(int temperature) {
        return settingMapper.getDto(
                settingRepository.newTemperature(temperature)
        );
    }

    @Override
    public void deleteTemperature(long id) {
        settingRepository.deleteTemperature(id);
    }

    @Override
    public StatusWindowBlindDto getStatusWindowBlind(long id) {
        return settingMapper.getDto(
                settingRepository.getStatus(id)
        );
    }

    @Override
    public StatusWindowBlindDto newStatusWindowBlind(StatusWindowBlindEnum status) {
        return settingMapper.getDto(
                settingRepository.newStatus(status)
        );
    }

    @Override
    public void deleteStatus(long id) {
        settingRepository.deleteStatus(id);
    }

    @Override
    public ActionCurtainsDto getTimeActionCurtains(long id) {
        return settingMapper.getDto(
                settingRepository.getAction(
                        id
                )
        );
    }

    @Override
    public ActionCurtainsDto newTimeActionCurtains(LocalTime time, ActionCurtainsEnum actionCurtainsEnum) {
        return settingMapper.getDto(
                settingRepository.newAction(
                        time,
                        actionCurtainsEnum
                )
        );
    }

    @Override
    public void deleteTimeActionCurtains(long id) {
        settingRepository.deleteAction(id);
    }


}
