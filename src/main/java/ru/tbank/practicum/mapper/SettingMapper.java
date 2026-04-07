package ru.tbank.practicum.mapper;

import ru.tbank.practicum.controller.dto.*;
import ru.tbank.practicum.entity.*;
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

    public CreateUserDto getDto(Users users, Devices devices, WindowBlindSettings windowBlindSettings,
                          BatterySettings batterySettings, WindowBlindAction openAction,
                          WindowBlindAction closeAction, BatteryTemp minTemp, BatteryTemp maxTemp) {
        return new CreateUserDto(
                users.getId(),
                devices.getId(),
                windowBlindSettings.getId(),
                batterySettings.getId(),
                openAction.getId(),
                closeAction.getId(),
                minTemp.getId(),
                maxTemp.getId()
        );
    }

    public UserDto getDto(Users users) {
        return new UserDto(
                users.getId(),
                users.getLogin()
        );
    }

    public DevicesDto getDto(Devices devices) {
        return new DevicesDto(devices.getId(),
                devices.getUser(),
                devices.getBattery(),
                devices.getWindowBlind());
    }

    public TemperatureDto getDtoBatterySettings(Optional<BatterySettings> batterySettings) {
        if (batterySettings.isEmpty()) {
            return new TemperatureDto(
                    null,
                    null,
                    null
            );
        }
        return new TemperatureDto(
                batterySettings.get().getId(),
                batterySettings.get().getMinTemp(),
                batterySettings.get().getMaxTemp()
        );
    }

    public BatteryTempDto getDtoBatteryTemp(Optional<BatteryTemp> batteryTemp) {
        if (batteryTemp.isEmpty()) {
            return new BatteryTempDto(
                    null,
                    null,
                    null
            );
        }

        return new BatteryTempDto(
                batteryTemp.get().getId(),
                batteryTemp.get().getTemp(),
                batteryTemp.get().getSetTemp()
        );
    }

    public StatusWindowBlindDto getDtoWindowStatus(Optional<WindowBlindSettings> windowBlindSettings) {
        if (windowBlindSettings.isEmpty()) {
            return new StatusWindowBlindDto(
                    null,
                    null,
                    null
            );
        }

        return new StatusWindowBlindDto(
                windowBlindSettings.get().getId(),
                windowBlindSettings.get().getOpenAction(),
                windowBlindSettings.get().getCloseAction()
        );
    }

    public WindowActionDto getDtoWindowAction(Optional<WindowBlindAction> windowBlindAction) {
        if (windowBlindAction.isEmpty()) {
            return new WindowActionDto(
                    null,
                    null,
                    null,
                    null
            );
        }

        return new WindowActionDto(
                windowBlindAction.get().getId(),
                windowBlindAction.get().getTimeStart(),
                windowBlindAction.get().getTimeEnd(),
                windowBlindAction.get().getStatus()
        );
    }

}