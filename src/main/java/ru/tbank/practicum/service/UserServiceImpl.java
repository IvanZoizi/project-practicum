package ru.tbank.practicum.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tbank.practicum.controller.dto.CreateUserDto;
import ru.tbank.practicum.controller.dto.DevicesDto;
import ru.tbank.practicum.controller.dto.LoginRequest;
import ru.tbank.practicum.controller.dto.UserDto;
import ru.tbank.practicum.entity.*;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.*;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final WindowBlindSettingsRepository windowBlindSettingsRepository;
    private final BatterySettingsRepository batterySettingsRepository;
    private final WindowBlindActionRepository windowBlindActionRepository;
    private final BatteryTempRepository batteryTempRepository;
    private final SettingMapper settingMapper;

    @Override
    public CreateUserDto createUser(LoginRequest loginRequest) {
        Users users = new Users();
        users.setLogin(loginRequest.getLogin());
        userRepository.save(users);

        WindowBlindSettings windowBlindSettings = new WindowBlindSettings();
        windowBlindSettingsRepository.save(windowBlindSettings);

        WindowBlindAction openAction = new WindowBlindAction();
        openAction.setTimeStart(LocalDateTime.now());
        openAction.setStatus(ActionStatus.COMPLETED);
        openAction.setTimeEnd(LocalDateTime.now());
        windowBlindActionRepository.save(openAction);

        WindowBlindAction closeAction = new WindowBlindAction();
        closeAction.setTimeStart(LocalDateTime.now());
        closeAction.setStatus(ActionStatus.COMPLETED);
        closeAction.setTimeEnd(LocalDateTime.now());
        windowBlindActionRepository.save(closeAction);

        windowBlindSettings.setOpenAction(openAction);
        windowBlindSettings.setCloseAction(closeAction);

        BatterySettings batterySettings = new BatterySettings();
        batterySettingsRepository.save(batterySettings);

        BatteryTemp minTemp = new BatteryTemp();
        minTemp.setTemp(-10L);
        minTemp.setSetTemp(20L);
        batteryTempRepository.save(minTemp);

        BatteryTemp maxTemp = new BatteryTemp();
        maxTemp.setTemp(20L);
        maxTemp.setSetTemp(5L);
        batteryTempRepository.save(maxTemp);

        batterySettings.setMaxTemp(maxTemp);
        batterySettings.setMinTemp(minTemp);

        Devices devices = new Devices();
        devices.setUser(users);
        devices.setWindowBlind(windowBlindSettings);
        devices.setBattery(batterySettings);
        deviceRepository.save(devices);

        return settingMapper.getDto(users, devices,
                windowBlindSettings, batterySettings, openAction, closeAction, minTemp, maxTemp);
    }

    @Override
    public UserDto getUser(Long id) {
        return settingMapper.getDto(userRepository.findById(id).get());
    }

    @Override
    public DevicesDto getDevices(Long id) {
        return settingMapper.getDto(deviceRepository.findById(id).get());
    }
}
