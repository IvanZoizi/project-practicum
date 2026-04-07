package ru.tbank.practicum.service;

import ru.tbank.practicum.controller.dto.CreateUserDto;
import ru.tbank.practicum.controller.dto.DevicesDto;
import ru.tbank.practicum.controller.dto.LoginRequest;
import ru.tbank.practicum.controller.dto.UserDto;
import ru.tbank.practicum.entity.Devices;

public interface UserService {
    CreateUserDto createUser(LoginRequest loginRequest);
    UserDto getUser(Long id);

    DevicesDto getDevices(Long id);
}
