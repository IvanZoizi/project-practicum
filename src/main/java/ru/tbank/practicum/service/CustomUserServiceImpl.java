package ru.tbank.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tbank.practicum.mapper.SettingMapper;
import ru.tbank.practicum.repository.UsersRepository;
import ru.tbank.practicum.security.CustomUserDetail;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl {

    private final SettingMapper settingMapper;

    private final UsersRepository usersRepository;

    public CustomUserDetail getUserByLogin(String login) {
        return settingMapper.getDto(usersRepository.findByLogin(login), login);
    }
}
